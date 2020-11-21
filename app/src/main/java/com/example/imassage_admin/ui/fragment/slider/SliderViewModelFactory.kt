package com.example.imassage_admin.ui.fragment.slider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SliderViewModelFactory(
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SliderViewModel() as T
    }
}