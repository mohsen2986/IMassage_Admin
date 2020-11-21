package com.example.imassage_admin.ui.fragment.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ConfigViewModelFactory(
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConfigViewModel() as T
    }
}