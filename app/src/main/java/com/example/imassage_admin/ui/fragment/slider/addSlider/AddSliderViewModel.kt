package com.example.imassage_admin.ui.fragment.slider.addSlider

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository
import okhttp3.MultipartBody

class AddSliderViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    suspend fun uploadSlider(image: MultipartBody.Part, title: String, description: String) =
        dataRepository.uploadSlider(image , title , description)

}