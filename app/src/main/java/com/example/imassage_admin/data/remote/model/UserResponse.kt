package com.example.imassage_admin.data.remote.model

import com.example.imassage_admin.data.model.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
        @SerializedName("data")
        val data: List<User> ,
        @SerializedName("meta")
        val metadata: MetaData
)