package com.shzlabs.mamopay.ui.signin.setup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent
import com.shzlabs.mamopay.data.DataManager
import com.shzlabs.mamopay.data.local.prefs.UserPreferences
import com.shzlabs.mamopay.ui.base.BaseViewModel
import javax.inject.Inject

class SignInSetupViewModel @Inject constructor (val dataManager: DataManager, val prefs: UserPreferences) : BaseViewModel(){

    var state = STATE.SETUP
    private var enteredString = ""
    private var pinToConfirm = ""

    private val _onCodeUpdate = MutableLiveData<String>()
    private val _onPinSet = LiveEvent<String>()
    private val _onLoginSuccess = MutableLiveData<Boolean>()

    val onCodeUpdate: LiveData<String>
        get() = _onCodeUpdate

    val onPinSet: LiveEvent<String>
        get() = _onPinSet

    val onLoginSuccess: LiveData<Boolean>
        get() = _onLoginSuccess

    fun onNumberPress(num: Int) {
        if (enteredString.length >= 4) return

        enteredString += "$num"
        _onCodeUpdate.value = enteredString
    }

    fun onDeletePress() {
        enteredString = enteredString.dropLast(1)
        _onCodeUpdate.value = enteredString
    }


    fun onNumberPressComplete() {

        if (state == STATE.SETUP) {
            _onPinSet.value = enteredString
            return
        }

        if (enteredString == pinToConfirm) {
            // Pin confirmed, save to local storage
            // todo save as hash
            prefs.setPinHash(enteredString)

            _onLoginSuccess.value = true

        } else {
            _onLoginSuccess.value = false
        }

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

    fun setConfirmCode(confirmCode: String) {
        state = STATE.CONFIRM
        pinToConfirm = confirmCode
    }

    enum class STATE {
        SETUP,CONFIRM
    }

}
