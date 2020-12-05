package com.example.imassage_admin.ui.fragment.phoneVerification

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.UserInformationRepository

class PhoneVerificationViewModel(
        private val userInformationRepository: UserInformationRepository
) : ViewModel() {
    suspend fun sendVerificationCode(code: String , type: String) =
            if(type == "register" ) userInformationRepository.registerVerifySms(code)
            else userInformationRepository.loginVerifySms(code)
}