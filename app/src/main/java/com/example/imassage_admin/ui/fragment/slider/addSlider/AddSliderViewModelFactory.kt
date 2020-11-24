package com.example.imassage_admin.ui.fragment.slider.addSlider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imassage_admin.data.repository.DataRepository

class AddSliderViewModelFactory(
    private val dataRepository: DataRepository
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddSliderViewModel(dataRepository) as T
    }
}