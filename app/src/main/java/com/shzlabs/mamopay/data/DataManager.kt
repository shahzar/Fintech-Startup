package com.shzlabs.mamopay.data

import com.shzlabs.mamopay.data.repository.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DataManager @Inject constructor(val remoteDataSrc: ApiService) {

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T) = withContext(Dispatchers.IO) {
        try {
            apiCall.invoke()
        } catch (throwable: Throwable) {
            when(throwable) {
                is IOException -> throw IOException("Network Error. Please check your internet.")
                is HttpException -> throw Exception("Error: ${throwable.message()}")
                else -> throw Exception("Some error occured")
            }
        }
    }

    suspend fun getSampleData() = safeApiCall {
        remoteDataSrc.getSampleData()
    }
}