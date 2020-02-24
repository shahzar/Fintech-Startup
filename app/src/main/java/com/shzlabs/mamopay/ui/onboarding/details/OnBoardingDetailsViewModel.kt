package com.shzlabs.mamopay.ui.onboarding.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shzlabs.mamopay.data.local.prefs.UserPreferences
import com.shzlabs.mamopay.ui.base.BaseViewModel
import javax.inject.Inject

class OnBoardingDetailsViewModel @Inject constructor (val prefs: UserPreferences) : BaseViewModel(){

    private val _firstname = MutableLiveData<String>()
    private val _lastname = MutableLiveData<String>()

    val firstname: LiveData<String>
        get() = _firstname

    val lastname: LiveData<String>
        get() = _lastname


    init {
        if (_firstname.value == null)
            _firstname.value = prefs.getFirstName()

        if (_lastname.value == null)
            _lastname.value = prefs.getLastName()
    }

    fun onCreate() {


    }

    fun setName(first: String, last: String) {

        if (first.isNotEmpty()) {
            prefs.setFirstName(first)
        }

        if (last.isNotEmpty()) {
            prefs.setLastName(last)
        }
    }

}
