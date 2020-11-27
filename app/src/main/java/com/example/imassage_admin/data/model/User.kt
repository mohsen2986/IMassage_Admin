package com.example.imassage_admin.data.model

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("id")
        val id: String ,
        @SerializedName("name")
        val name: String ,
        @SerializedName("family")
        val family: String ,
        @SerializedName("phone")
        val phone: String ,
        @SerializedName("photo")
        val photo: String ,
        @SerializedName("gender")
        val gender: String
)