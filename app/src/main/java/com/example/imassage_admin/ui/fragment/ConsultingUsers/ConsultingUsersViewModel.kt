package com.example.imassage_admin.ui.fragment.ConsultingUsers

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository

class ConsultingUsersViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    suspend fun getConsultingUsers() =
        dataRepository.getConsulting()

    suspend fun setConsultingUser(userId: String) =
        dataRepository.setConsultingUser(userId)
}