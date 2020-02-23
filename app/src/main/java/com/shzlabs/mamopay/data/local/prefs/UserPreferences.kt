package com.shzlabs.mamopay.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_AUTH_STATE = "PREF_KEY_AUTH_STATE"
        const val KEY_PIN_HASH = "PREF_KEY_PIN_HASH"
    }

    fun setAuthState(authState: String) =
        prefs.edit().putString(KEY_AUTH_STATE, authState).apply()

    fun getAuthState(): String? =
        prefs.getString(KEY_AUTH_STATE, null)

    fun removeAuthState() =
        prefs.edit().remove(KEY_AUTH_STATE).apply()

    fun setPinHash(authState: String) =
        prefs.edit().putString(KEY_PIN_HASH, authState).apply()

    fun getPinHash(): String? =
        prefs.getString(KEY_PIN_HASH, null)

    fun removePinHash() =
        prefs.edit().remove(KEY_PIN_HASH).apply()


}