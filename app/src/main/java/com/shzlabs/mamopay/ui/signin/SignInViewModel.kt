package com.shzlabs.mamopay.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent
import com.shzlabs.mamopay.data.DataManager
import com.shzlabs.mamopay.data.local.prefs.UserPreferences
import com.shzlabs.mamopay.data.model.SampleDataModel
import com.shzlabs.mamopay.ui.base.BaseViewModel
import javax.inject.Inject

class SignInViewModel @Inject constructor (val prefs: UserPreferences) : BaseViewModel(){

    private var enteredPin = ""

    private val _firsname = MutableLiveData<String>()
    private val _onCodeUpdate = MutableLiveData<String>()
    private val _onLoginSuccess = MutableLiveData<Boolean>()

    val firstName: LiveData<String>
        get() = _firsname

    val onCodeUpdate: LiveData<String>
        get() = _onCodeUpdate

    val onLoginSuccess: LiveData<Boolean>
        get() = _onLoginSuccess

    fun onCreate() {
        fetchName()
    }

    fun fetchName() {
        prefs.getFirstName()?.let {
            _firsname.value = it
        }
    }

    fun onNumberPress(num: Int) {
        if (enteredPin.length >= 4) return

        enteredPin += "$num"
        _onCodeUpdate.value = enteredPin
    }

    fun onDeletePress() {
        enteredPin = enteredPin.dropLast(1)
        _onCodeUpdate.value = enteredPin
    }


    fun onNumberPressComplete() {

        val actualPin = prefs.getPinHash()
        val isMatch = enteredPin == actualPin

        _onLoginSuccess.value = isMatch

    }

    fun isPinAuthenticated(): Boolean {
        prefs.getPinHash()?.let {
            return it.isNotEmpty()
        }

        return false
    }

    fun biometricAuthSuccess(success: Boolean) {
        if (success) {
            prefs.setBiometricAuth(true)
        }
    }

}
