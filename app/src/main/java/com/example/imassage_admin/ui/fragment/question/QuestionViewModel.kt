package com.example.imassage_admin.ui.fragment.question

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository

class QuestionViewModel(
        private val dataRepository: DataRepository
) : ViewModel() {

    suspend fun questions() =
            dataRepository.question()

    suspend fun addQuestion(question: String) =
            dataRepository.addQuestion(question)

    suspend fun deleteQuestion(id: String) =
            dataRepository.deleteQuestion(id)
}