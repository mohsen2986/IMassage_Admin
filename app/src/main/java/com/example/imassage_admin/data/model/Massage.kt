package com.example.imassage_admin.data.model

import com.google.gson.annotations.SerializedName

data class  Massage(
        @SerializedName("name")
        val name: String ,
        @SerializedName("cost")
        val cost: String ,
        @SerializedName("length")
        val length: String ,
        @SerializedName("image")
        val image: String ,
        @SerializedName("description")
        val description: String ,
        @SerializedName("id")
        val id: String
)