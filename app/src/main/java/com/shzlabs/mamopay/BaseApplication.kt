package com.shzlabs.mamopay

import android.app.Application
import com.shzlabs.mamopay.di.modules.AppModule
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.di.components.DaggerAppComponent

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.application(this)
    }

}