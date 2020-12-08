package com.example.imassage_admin.data.model

import com.google.gson.annotations.SerializedName

data class Offer(
        @SerializedName("code")
        val code: String ,
        @SerializedName("data")
        val data: String ,
        @SerializedName("validate")
        val validate: String ,
        @SerializedName("offer")
        val offer: String ,
        @SerializedName("massage")
        val massage: String ,
        @SerializedName("start_date")
        val startDate: String ,
        @SerializedName("expire_date")
        val expireDate: String
)