package com.example.imassage_admin.ui.fragment.question.addQuestion

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository

class AddQuestionViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    suspend fun addQuestion(question: String) =
            dataRepository.addQuestion(question)

}