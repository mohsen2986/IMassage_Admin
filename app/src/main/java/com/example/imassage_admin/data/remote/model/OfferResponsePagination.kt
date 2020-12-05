package com.example.imassage_admin.data.remote.model

import com.example.imassage_admin.data.model.Offer
import com.example.imassage_admin.data.model.User
import com.google.gson.annotations.SerializedName

data class OfferResponsePagination(
        @SerializedName("data")
        val data: List<Offer>,
        @SerializedName("meta")
        val metadata: MetaData
)