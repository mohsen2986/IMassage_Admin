package com.example.imassage_admin.ui.fragment.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imassage_admin.data.repository.DataRepository

class UsersViewModelFactory(
        private val dataRepository: DataRepository
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UsersViewModel(dataRepository) as T
    }
}