package com.example.imassage_admin.data.remote.model

import com.google.gson.annotations.SerializedName

data class RegisterationError(
        @SerializedName("email")
        val email: List<String> ,
        @SerializedName("name")
        val name: List<String> ,
        @SerializedName("password")
        val password: List<String> ,
        @SerializedName("family")
        val family: List<String> ,
        @SerializedName("gender")
        val gender: List<String> ,
        @SerializedName("phone")
        val phone: List<String>
)