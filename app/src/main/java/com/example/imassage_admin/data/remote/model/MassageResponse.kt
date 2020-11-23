package com.example.imassage_admin.data.remote.model

import com.example.imassage_admin.data.model.Massage
import com.google.gson.annotations.SerializedName

data class MassageResponse(
        @SerializedName("datas")
        val datas: List<Massage>
)