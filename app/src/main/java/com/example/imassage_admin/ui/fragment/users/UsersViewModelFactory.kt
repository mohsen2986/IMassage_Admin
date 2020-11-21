package com.example.imassage_admin.ui.fragment.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UsersViewModelFactory(
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UsersViewModel() as T
    }
}