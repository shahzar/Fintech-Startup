package com.shzlabs.mamopay.util.biometric

import android.util.Log
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.shzlabs.mamopay.ui.base.BaseFragment

object BiometricHelper {

    fun instanceOfBiometricPrompt(baseFragment: BaseFragment,
                                          onCancel: (msg: String) -> Unit,
                                          onSuccess: () -> Unit): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(baseFragment.context)

        val callback = object: BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                onCancel("$errorCode :: $errString")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Log.e(BiometricHelper.javaClass.simpleName, "Authentication failed for an unknown reason")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                //Toaster.show(applicationContext, "Authentication was successful")
                onSuccess()
            }
        }

        val biometricPrompt = BiometricPrompt(baseFragment, executor, callback)
        return biometricPrompt
    }
}