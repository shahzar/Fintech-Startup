package com.shzlabs.mamopay.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shzlabs.mamopay.data.model.TransactionModel
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        TransactionModel::class
    ],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
}