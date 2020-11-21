package com.example.imassage_admin.ui.fragment.packages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PackageViewModelFactory(
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PackagesViewModel() as T
    }
}