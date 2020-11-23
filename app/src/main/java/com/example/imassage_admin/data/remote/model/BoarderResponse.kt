package com.example.imassage_admin.data.remote.model

import com.example.imassage_admin.data.model.Boarder
import com.google.gson.annotations.SerializedName

data class BoarderResponse(
        @SerializedName("datas")
        val boarders: List<Boarder>
)