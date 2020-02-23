package com.shzlabs.mamopay.ui.signin.setup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent
import com.shzlabs.mamopay.data.DataManager
import com.shzlabs.mamopay.ui.base.BaseViewModel
import javax.inject.Inject

class SignInSetupViewModel @Inject constructor (val dataManager: DataManager) : BaseViewModel(){

    var state = STATE.SETUP
    private var enteredString = ""
    private var pinToConfirm = ""

    private val _onCodeUpdate = MutableLiveData<String>()
    private val _onPinSet = LiveEvent<String>()
    private val _onLoginSuccess = MutableLiveData<Boolean>()
    private val _onLoginFailed = MutableLiveData<Boolean>()

    val onCodeUpdate: LiveData<String>
        get() = _onCodeUpdate

    val onPinSet: LiveEvent<String>
        get() = _onPinSet

    val onLoginSuccess: LiveData<Boolean>
        get() = _onLoginSuccess


    val onLoginFailed: LiveData<Boolean>
        get() = _onLoginFailed

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
            _onLoginSuccess.value = true
        } else {
            _onLoginFailed.value = true
        }

//        if (enteredString == "5678") {
//            _onLoginSuccess.value = true
//        } else {
//            _onLoginFailed.value = true
//        }
    }

    fun setConfirmCode(confirmCode: String) {
        state = STATE.CONFIRM
        pinToConfirm = confirmCode
    }

    enum class STATE {
        SETUP,CONFIRM
    }

}
