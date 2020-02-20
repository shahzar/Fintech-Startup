package com.shzlabs.mamopay.data.repository

import com.shzlabs.mamopay.data.model.SampleDataModel
import retrofit2.http.GET

interface ApiService {

    @GET("todos/1")
    suspend fun getSampleData(): SampleDataModel

}