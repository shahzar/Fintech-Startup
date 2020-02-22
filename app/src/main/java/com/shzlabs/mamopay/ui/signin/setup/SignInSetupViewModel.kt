package com.shzlabs.mamopay.ui.signin.setup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shzlabs.mamopay.data.DataManager
import com.shzlabs.mamopay.data.model.SampleDataModel
import com.shzlabs.mamopay.ui.base.BaseViewModel
import javax.inject.Inject

class SignInSetupViewModel @Inject constructor (val dataManager: DataManager) : BaseViewModel(){

    private var enteredString = ""

    //private val _
    private val _onCodeUpdate = MutableLiveData<String>()
    private val _onLoginSuccess = MutableLiveData<Boolean>()
    private val _onLoginFailed = MutableLiveData<Boolean>()

    val onCodeUpdate: LiveData<String>
        get() = _onCodeUpdate

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

        if (enteredString == "5678") {
            _onLoginSuccess.value = true
        } else {
            _onLoginFailed.value = true
        }
    }



}
