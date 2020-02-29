package com.shzlabs.mamopay.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.data.local.database.DatabaseService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    fun getString(): String {
        return application.getString(R.string.app_name)
    }

    @Provides
    fun getContext(): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideSharedPreferences() = application.getSharedPreferences("mamopay-prefs", Context.MODE_PRIVATE)

    @Provides
    fun provideDatabaseService(context: Context): DatabaseService {
        return Room.databaseBuilder(context, DatabaseService::class.java, "mamopay-db").build()
    }

}