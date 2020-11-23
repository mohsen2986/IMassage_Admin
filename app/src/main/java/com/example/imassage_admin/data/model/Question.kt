package com.example.imassage_admin.data.model

import com.google.gson.annotations.SerializedName

data class Question(
        @SerializedName("id")
        val questionId: String ,
        @SerializedName("question")
        val question: String ,
        @SerializedName("question_type_id")
        val questionType: String
)