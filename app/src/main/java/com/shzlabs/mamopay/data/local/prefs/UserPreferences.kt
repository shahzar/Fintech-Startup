package com.shzlabs.mamopay.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_AUTH_STATE = "PREF_KEY_AUTH_STATE"
        const val KEY_PIN_HASH = "PREF_KEY_PIN_HASH"
        const val KEY_BIOMETRIC_AUTH_SET = "PREF_KEY_BIOMETRIC_AUTH_SET"
        const val KEY_FIRST_NAME = "PREF_KEY_FIRST_NAME"
        const val KEY_LAST_NAME = "PREF_KEY_LAST_NAME"
    }

    fun setAuthState(authState: String) =
        prefs.edit().putString(KEY_AUTH_STATE, authState).apply()

    fun getAuthState(): String? =
        prefs.getString(KEY_AUTH_STATE, null)

    fun removeAuthState() =
        prefs.edit().remove(KEY_AUTH_STATE).apply()

    fun setFirstName(firstName: String) =
        prefs.edit().putString(KEY_FIRST_NAME, firstName).apply()

    fun getFirstName(): String? =
        prefs.getString(KEY_FIRST_NAME, null)

    fun removeFirstName() =
        prefs.edit().remove(KEY_FIRST_NAME).apply()

    fun setLastName(lastName: String) =
        prefs.edit().putString(KEY_LAST_NAME, lastName).apply()

    fun getLastName(): String? =
        prefs.getString(KEY_LAST_NAME, null)

    fun removeLastName() =
        prefs.edit().remove(KEY_LAST_NAME).apply()

    fun setPinHash(pin: String) =
        prefs.edit().putString(KEY_PIN_HASH, pin).apply()

    fun getPinHash(): String? =
        prefs.getString(KEY_PIN_HASH, null)

    fun removePinHash() =
        prefs.edit().remove(KEY_PIN_HASH).apply()

    fun setBiometricAuth(success: Boolean) =
        prefs.edit().putBoolean(KEY_BIOMETRIC_AUTH_SET, success).apply()

    fun getBiometricAuth(): Boolean =
        prefs.getBoolean(KEY_BIOMETRIC_AUTH_SET, false)

    fun removeBiometricAuth() =
        prefs.edit().remove(KEY_BIOMETRIC_AUTH_SET).apply()


}