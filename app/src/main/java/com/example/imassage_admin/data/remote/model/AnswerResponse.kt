package com.example.imassage_admin.data.remote.model

import com.example.imassage_admin.data.model.Answer
import com.google.gson.annotations.SerializedName

data class AnswerResponse(
        @SerializedName("datas")
        val answers: List<Answer>
)