package com.shzlabs.mamopay.ui.main.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent
import com.shzlabs.mamopay.data.DataManager
import com.shzlabs.mamopay.data.local.database.DatabaseService
import com.shzlabs.mamopay.data.model.SampleDataModel
import com.shzlabs.mamopay.data.model.TransactionModel
import com.shzlabs.mamopay.data.seed.SeedTransactionData
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseViewModel
import com.shzlabs.mamopay.util.display.Toaster
import com.shzlabs.mamopay.util.logger.Logger
import kotlinx.coroutines.delay
import javax.inject.Inject

class DashboardViewModel @Inject constructor (private val dataManager: DataManager) : BaseViewModel(){

    private val _transactionData = MutableLiveData<List<TransactionModel>>()
    private val _balance = MutableLiveData<Long>()
    private val _currency = MutableLiveData<String>()
    private val _moneyAddProgress = MutableLiveData<Boolean>()
    private val _onMoneyAdded = LiveEvent<Int>()

    val transactionData: LiveData<List<TransactionModel>>
        get() = _transactionData

    val balanceData: LiveData<Long>
        get() = _balance

    val currency: LiveData<String>
        get() = _currency

    val moneyAddProgress: MutableLiveData<Boolean>
        get() = _moneyAddProgress

    val onMoneyAdded: LiveEvent<Int>
        get() = _onMoneyAdded


    init {
        _balance.value = 2000
        _currency.value = "AED"
    }

    fun getTransactionData() {
        ioLaunch(
            block = {
                var data = dataManager.getTransactionData()

                if (data.isEmpty()) {
                    SeedTransactionData.init(dataManager)
                    data = dataManager.getTransactionData()
                }

                data
            },
            onSuccess = {
                _transactionData.value = it
            }
        )
    }

    fun addMoney(amount: Int) {

        val balance = _balance.value
        _moneyAddProgress.value = true

        ioLaunch(
            block = {
                // TODO: interviewer review
                dataManager.addMoney()
            },
            onSuccess = {
                _onMoneyAdded.value = amount
                _moneyAddProgress.value = false

                // TODO: interviewer review
                _balance.value = if (balance == null) { amount.toLong() } else { balance + amount }

            },
            onFailure = {
                _onError.value = "Error adding money"
                _moneyAddProgress.value = false
            }
        )
    }

}
