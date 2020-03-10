package com.shzlabs.mamopay.ui.main.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent
import com.shzlabs.mamopay.data.DataManager
import com.shzlabs.mamopay.data.model.TransactionModel
import com.shzlabs.mamopay.data.seed.SeedTransactionData
import com.shzlabs.mamopay.ui.base.BaseViewModel
import javax.inject.Inject

class DashboardViewModel @Inject constructor (private val dataManager: DataManager) : BaseViewModel(){

    private val _transactionData = MutableLiveData<List<TransactionModel>>()
    private val _balance = MutableLiveData<Long>()
    private val _balanceFetchComplete = LiveEvent<Long>()
    private val _currency = MutableLiveData<String>()
    private val _moneyAddProgress = MutableLiveData<Boolean>()
    private val _onMoneyAdded = LiveEvent<Int>()

    val transactionData: LiveData<List<TransactionModel>>
        get() = _transactionData

    val balanceData: LiveData<Long>
        get() = _balance

    val balanceFetchComplete: LiveData<Long>
        get() = _balanceFetchComplete

    val currency: LiveData<String>
        get() = _currency

    val moneyAddProgress: MutableLiveData<Boolean>
        get() = _moneyAddProgress

    val onMoneyAdded: LiveEvent<Int>
        get() = _onMoneyAdded


    init {
        _currency.value = "AED"
    }

    fun fetchBalance() {
        ioLaunch(
            block = {
                dataManager.fetchUserBalance()
            },
            onSuccess = {
                _balanceFetchComplete.value = 2000
                _balance.value = 2000
            }
        )
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
                val balance = _balance.value
                _balance.value = if (balance == null) { amount.toLong() } else { balance + amount }

            },
            onFailure = {
                _onError.value = "Error adding money"
                _moneyAddProgress.value = false
            }
        )
    }

}
