package com.shzlabs.mamopay.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_AUTH_STATE = "PREF_KEY_AUTH_STATE"
    }

    fun setAuthState(authState: String) =
        prefs.edit().putString(KEY_AUTH_STATE, authState).apply()

    fun getAuthState(): String? =
        prefs.getString(KEY_AUTH_STATE, null)

    fun removeAuthState() =
        prefs.edit().remove(KEY_AUTH_STATE).apply()

}