package com.example.imassage_admin.data.model
import com.google.gson.annotations.SerializedName


data class Order(
        @SerializedName("filled_form")
        val filledForm: FilledForm,
        @SerializedName("massage")
        val massage: Massage,
        @SerializedName("package")
        val package_: String,
        @SerializedName("reserve_time")
        val reservedTimeDateId: DateTimes,
        @SerializedName("transaction")
        val transactions: Transactions,
        @SerializedName("user")
        val user: User ,
        @SerializedName("times")
        val times: List<Times>
)