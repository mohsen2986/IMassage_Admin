package com.example.imassage_admin.data.model

import com.example.imassage_admin.data.model.Question
import com.google.gson.annotations.SerializedName

data class Answer(
        @SerializedName("answer")
        val answer: String ,
        @SerializedName("question")
        val question: Question
)