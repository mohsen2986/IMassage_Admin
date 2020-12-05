package com.example.imassage_admin.data.remote.model

import com.google.gson.annotations.SerializedName

data class SmsVerificationResponse(
    @SerializedName("message")
    val message: String ,
    @SerializedName("token")
    val token: String
)