package com.example.imassage_admin.ui.fragment.showAnswers

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.model.FilledForm
import com.example.imassage_admin.data.repository.DataRepository

class ShowAnswersViewModel(
        private val dataRepository: DataRepository
    ) : ViewModel() {

    suspend fun answer(filledForm: String) =
        dataRepository.answers(filledForm)

}