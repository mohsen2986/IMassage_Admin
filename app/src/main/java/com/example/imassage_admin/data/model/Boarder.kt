package com.example.imassage_admin.data.model

import com.google.gson.annotations.SerializedName

data class Boarder(
        @SerializedName("id")
        val id: String ,
        @SerializedName("title")
        val title: String ,
        @SerializedName("image")
        val image: String ,
        @SerializedName("description")
        val description: String
)