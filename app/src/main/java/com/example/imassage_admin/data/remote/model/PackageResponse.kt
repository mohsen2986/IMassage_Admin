package com.example.imassage_admin.data.remote.model

import com.google.gson.annotations.SerializedName
import com.example.imassage_admin.data.model.Package

data class PackageResponse(
        @SerializedName("datas")
        val datas:List<Package>
)
//massage_id