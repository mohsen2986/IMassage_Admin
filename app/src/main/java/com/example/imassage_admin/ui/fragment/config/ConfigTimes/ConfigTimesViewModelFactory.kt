package com.example.imassage_admin.ui.fragment.config.ConfigTimes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imassage_admin.data.repository.DataRepository

class ConfigTimesViewModelFactory(
        private val dataRepository: DataRepository
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConfigTimesViewModel(dataRepository) as T
    }
}