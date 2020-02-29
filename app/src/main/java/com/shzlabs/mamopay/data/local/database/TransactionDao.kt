package com.shzlabs.mamopay.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.shzlabs.mamopay.data.model.TransactionModel

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transaction_data")
    fun getAll(): List<TransactionModel>

    @Insert
    fun insert(entity: TransactionModel)

    @Delete
    fun delete(entity: TransactionModel)
}