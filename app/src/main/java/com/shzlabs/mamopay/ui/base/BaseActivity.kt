package com.shzlabs.mamopay.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.BaseApplication
import com.shzlabs.mamopay.NavMgr

abstract class BaseActivity: AppCompatActivity() {

    fun getDiComponent() : AppComponent {
        return (application as BaseApplication).appComponent
    }

    override fun onBackPressed() {
        if(!NavMgr().pop(this)) {
            finish()
        }
    }

}