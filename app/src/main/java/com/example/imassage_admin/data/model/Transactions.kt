package com.example.imassage_admin.data.model

import com.google.gson.annotations.SerializedName

data class Transactions(
        @SerializedName("id")
        val id: String ,
        @SerializedName("ref_id")
        val ref_id: String ,
        @SerializedName("amount")
        val amount: String ,
        @SerializedName("amount_with_offer")
        val amountWithOffer: String
)