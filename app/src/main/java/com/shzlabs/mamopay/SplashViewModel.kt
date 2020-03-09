package com.shzlabs.mamopay

import com.shzlabs.mamopay.data.local.prefs.UserPreferences
import com.shzlabs.mamopay.util.helper.GoogleAuthHelper
import com.shzlabs.mamopay.ui.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(val prefs: UserPreferences) : BaseViewModel() {

    fun isUserLoggedIn(): Boolean{

        prefs.getAuthState()?.let {

            return GoogleAuthHelper.isLoggedIn(it)

        }

        return false
    }

}