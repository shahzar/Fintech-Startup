package com.shzlabs.mamopay.data.seed

import com.shzlabs.mamopay.data.DataManager
import com.shzlabs.mamopay.data.model.TransactionModel

object SeedTransactionData {

    suspend fun init(dataManager: DataManager) {

        dataManager.addTransactionData(createObj("Hayfa Saliba", 350.0, TransactionModel.TYPE_RECEIVED))
        dataManager.addTransactionData(createObj("Karima Tahan", 2590.0, TransactionModel.TYPE_SENT))
        dataManager.addTransactionData(createObj("Moe Khalifa", 905.0, TransactionModel.TYPE_RECEIVED))
        dataManager.addTransactionData(createObj("Sarah Aliherma", 200.0, TransactionModel.TYPE_RECEIVED))
        dataManager.addTransactionData(createObj("Talal Shamoun", 450.0, TransactionModel.TYPE_RECEIVED))
        dataManager.addTransactionData(createObj("Coman Quraishi", 1100.0, TransactionModel.TYPE_SENT))

        dataManager.addTransactionData(createObj("Hayfa Saliba", 350.0, TransactionModel.TYPE_RECEIVED))
        dataManager.addTransactionData(createObj("Karima Tahan", 2590.0, TransactionModel.TYPE_SENT))
        dataManager.addTransactionData(createObj("Moe Khalifa", 905.0, TransactionModel.TYPE_RECEIVED))
        dataManager.addTransactionData(createObj("Sarah Aliherma", 200.0, TransactionModel.TYPE_RECEIVED))
        dataManager.addTransactionData(createObj("Talal Shamoun", 450.0, TransactionModel.TYPE_RECEIVED))
        dataManager.addTransactionData(createObj("Coman Quraishi", 1100.0, TransactionModel.TYPE_SENT))


    }


    private fun createObj(name: String, amount :Double, type: String): TransactionModel {
        val transactionModel = TransactionModel()
        transactionModel.name = name
        transactionModel.amount = amount
        transactionModel.currency = "AED"
        transactionModel.transactionState = type
        val time = "2020-02-29 10:00:00"
        transactionModel.created = time
        transactionModel.updated = time
        return transactionModel
    }
}