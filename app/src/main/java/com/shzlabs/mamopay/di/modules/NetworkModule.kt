package com.shzlabs.mamopay.di.modules

import com.google.gson.GsonBuilder
import com.shzlabs.mamopay.data.repository.ApiService
import com.shzlabs.mamopay.util.config.AppConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {



    @Singleton
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): ApiService {

        return Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build().create(ApiService::class.java)

    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {

        val okHttpBuilder = OkHttpClient.Builder()
        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())

}