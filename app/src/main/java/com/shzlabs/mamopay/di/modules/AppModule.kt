package com.shzlabs.mamopay.di.modules

import android.app.Application
import com.shzlabs.mamopay.R
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    fun getString(): String {
        return application.getString(R.string.app_name)
    }



}