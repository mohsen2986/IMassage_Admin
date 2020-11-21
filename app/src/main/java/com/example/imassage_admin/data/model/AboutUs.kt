package com.example.imassage_admin.data.model

import com.google.gson.annotations.SerializedName

data class AboutUs(
        @SerializedName("description")
        val description: String ,
        @SerializedName("image")
        val image: String

)