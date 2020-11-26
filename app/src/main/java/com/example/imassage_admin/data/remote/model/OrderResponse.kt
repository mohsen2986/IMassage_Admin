package com.example.imassage_admin.data.remote.model

import com.example.imassage_admin.data.model.Order
import com.example.imassage_admin.data.model.User
import com.google.gson.annotations.SerializedName

data class OrderResponse(
        @SerializedName("data")
        val data: List<Order>,
        @SerializedName("meta")
        val metadata: MetaData
)