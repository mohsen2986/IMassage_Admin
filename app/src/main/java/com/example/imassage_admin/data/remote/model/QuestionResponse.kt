package com.example.imassage_admin.data.remote.model

import com.example.imassage_admin.data.model.Question
import com.google.gson.annotations.SerializedName

data class QuestionResponse(
        @SerializedName("datas")
        val datas: List<Question>
)