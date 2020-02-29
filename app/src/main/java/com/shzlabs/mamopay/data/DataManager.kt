package com.shzlabs.mamopay.data

import com.shzlabs.mamopay.data.local.database.DatabaseService
import com.shzlabs.mamopay.data.model.TransactionModel
import com.shzlabs.mamopay.data.repository.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DataManager @Inject constructor(private val remoteDataSrc: ApiService, private val localDataSrc: DatabaseService) {

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

    suspend fun getTransactionData() = safeApiCall {
        localDataSrc.transactionDao().getAll()
    }

    suspend fun addTransactionData(transactionModel: TransactionModel) = safeApiCall {
        localDataSrc.transactionDao().insert(transactionModel)
    }

}