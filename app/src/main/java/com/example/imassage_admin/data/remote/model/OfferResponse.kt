package com.example.imassage_admin.data.remote.model

import com.example.imassage_admin.data.model.Boarder
import com.example.imassage_admin.data.model.Offer
import com.google.gson.annotations.SerializedName

data class OfferResponse(
        @SerializedName("datas")
        val boarders: List<Offer>
)