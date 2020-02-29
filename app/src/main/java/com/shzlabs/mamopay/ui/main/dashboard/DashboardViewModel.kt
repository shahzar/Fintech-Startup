package com.shzlabs.mamopay.ui.main.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shzlabs.mamopay.data.DataManager
import com.shzlabs.mamopay.data.local.database.DatabaseService
import com.shzlabs.mamopay.data.model.SampleDataModel
import com.shzlabs.mamopay.data.model.TransactionModel
import com.shzlabs.mamopay.data.seed.SeedTransactionData
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseViewModel
import com.shzlabs.mamopay.util.display.Toaster
import com.shzlabs.mamopay.util.logger.Logger
import javax.inject.Inject

class DashboardViewModel @Inject constructor (val dataManager: DataManager) : BaseViewModel(){

    private val _transactionData = MutableLiveData<List<TransactionModel>>()

    val transactionData: LiveData<List<TransactionModel>>
        get() = _transactionData

    fun getTransactionData() {

        ioLaunch(
            block = {
                var data = dataManager.getTransactionData()

                if (data.isEmpty()) {
                    SeedTransactionData.init(dataManager)
                    data = dataManager.getTransactionData()
                }

                return@ioLaunch data
            },
            onSuccess = {
                Logger.d(DashboardViewModel::class.java.simpleName, "Item count ${it.size}")
                _transactionData.value = it
            }
        )

        /*ioLaunch(
            block = {
                dataManager.getTransactionData()
            },
            onSuccess = {
                _sampleData.value = it
            }
        )*/

    }

}
