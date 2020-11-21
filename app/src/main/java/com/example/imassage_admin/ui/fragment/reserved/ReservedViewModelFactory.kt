package com.example.imassage_admin.ui.fragment.reserved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ReservedViewModelFactory(
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReservedViewModel() as T
    }
}