package com.example.imassage_admin.ui.fragment.registerForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imassage_admin.data.repository.UserInformationRepository

class RegisterFormViewModelFactory(
        private val userInformationRepository : UserInformationRepository
): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterFormViewModel(userInformationRepository) as T
    }

}