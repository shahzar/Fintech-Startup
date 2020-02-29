package com.shzlabs.mamopay.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "transaction_data")
data class TransactionModel(

    @PrimaryKey(autoGenerate = true)
    @NotNull
    var id: Long = 0,

    @ColumnInfo(name = "amount")
    @NotNull
    @SerializedName("amount")
    var amount: Double = 0.0,

    @ColumnInfo(name = "currency")
    @NotNull
    @SerializedName("currency")
    var currency: String = "",

    @ColumnInfo(name = "name")
    @NotNull
    @SerializedName("name")
    var name: String = "",

    @ColumnInfo(name = "transaction_state")
    @NotNull
    @SerializedName("transaction_state")
    var transactionState: String = "",

    @ColumnInfo(name = "updated")
    @SerializedName("updated")
    var updated: String = "",

    @ColumnInfo(name = "created")
    @SerializedName("created")
    var created: String = ""
) {

    companion object {
        const val TYPE_RECEIVED = "received"
        const val TYPE_SENT = "sent"
    }

}