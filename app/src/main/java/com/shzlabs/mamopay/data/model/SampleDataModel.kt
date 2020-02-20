package com.shzlabs.mamopay.data.model
import com.google.gson.annotations.SerializedName


data class SampleDataModel(
    @SerializedName("completed")
    var completed: Boolean = false,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("userId")
    var userId: Int = 0
) {

    override fun toString(): String {
        return super.toString()
    }
}