package com.example.imassage_admin.ui.fragment.login

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.UserInformationRepository

class LoginViewModel(
        private val userInformationRepository: UserInformationRepository
) : ViewModel() {
    suspend fun login(phone: String , password: String) =
            userInformationRepository.login(phone , password)
}