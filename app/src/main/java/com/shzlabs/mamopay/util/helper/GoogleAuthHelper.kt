package com.shzlabs.mamopay.util.helper

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import net.openid.appauth.*
import org.json.JSONException

object GoogleAuthHelper {

    private val LOG_TAG = "GoogleAuthHelper"

    fun initAuth(context: Context): Intent {
        val serviceConfiguration = AuthorizationServiceConfiguration(
            Uri.parse("https://accounts.google.com/o/oauth2/v2/auth") /* auth endpoint */,
            Uri.parse("https://www.googleapis.com/oauth2/v4/token") /* token endpoint */
        )

        val clientId = "565425653866-gedp0mqqrtent8un9tiadif53jkvgre5.apps.googleusercontent.com"

        val redirectUri = Uri.parse("com.shzlabs.mamopay:/oauth2callback")
        val builder = AuthorizationRequest.Builder(
            serviceConfiguration,
            clientId,
            ResponseTypeValues.CODE,
            redirectUri
        )
        builder.setScopes("profile")
        val request = builder.build()

        val authorizationService = AuthorizationService(context)

        val action = "com.shzlabs.mamopay.HANDLE_AUTHORIZATION_RESPONSE"
        val postAuthorizationIntent = Intent(action)
        val pendingIntent = PendingIntent.getActivity(
            context,
            request.hashCode(),
            postAuthorizationIntent,
            0
        )
        //authorizationService.performAuthorizationRequest(request, pendingIntent)
        return authorizationService.getAuthorizationRequestIntent(request)
    }

    fun processResponse(context: Context, intent: Intent, onSuccess: (String) -> Unit) {
        val response = AuthorizationResponse.fromIntent(intent)
        val error = AuthorizationException.fromIntent(intent)
        val authState = AuthState(response, error)

        if (response != null) {
            Log.i(LOG_TAG, String.format("Handled Authorization Response %s ", authState.jsonSerializeString()))
            val service = AuthorizationService(context)
            service.performTokenRequest(response.createTokenExchangeRequest()) { tokenResponse, exception ->
                if (exception != null) {
                    Log.w(LOG_TAG, "Token Exchange failed", exception)
                } else {
                    if (tokenResponse != null) {
                        authState.update(tokenResponse, exception)
                        //persistAuthState(authState)
                        Log.i(LOG_TAG, String.format("Token Response [ Access Token: %s, ID Token: %s ]",
                                tokenResponse.accessToken,
                                tokenResponse.idToken))

                        onSuccess(authState.jsonSerializeString())
                        //return authState.jsonSerializeString()
                    }
                }
            }
        }
    }

    fun isLoggedIn(authString: String?) : Boolean{
        try {
            authString?.let {
                val authState = AuthState.jsonDeserialize(it)

                if (authState != null && authState.isAuthorized) {
                    return true
                }
            }
            return false

        } catch (jsonException: JSONException) {
            Log.e(LOG_TAG, jsonException.message)
            return false
        }
    }

}