package com.example.imassage_admin.data.model

import com.google.gson.annotations.SerializedName

data class FilledForm(
        @SerializedName("id")
        var formId: String ,
        @SerializedName("date")
        var date: String
)