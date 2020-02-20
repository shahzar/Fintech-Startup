package com.shzlabs.mamopay.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.BaseApplication

abstract class BaseActivity: AppCompatActivity() {

    fun getDiComponent() : AppComponent {
        return (application as BaseApplication).appComponent
    }

}