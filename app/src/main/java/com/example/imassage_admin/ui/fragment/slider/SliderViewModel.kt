package com.example.imassage_admin.ui.fragment.slider

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository
import okhttp3.MultipartBody

class SliderViewModel(
        private val dataRepository: DataRepository
) : ViewModel() {

    suspend fun sliders() =
            dataRepository.getSliders()

    suspend fun uploadSlider(image: MultipartBody.Part , title: String , description: String) =
            dataRepository.uploadSlider(image , title , description)

    suspend fun deleteSlider(id: String) =
            dataRepository.deleteSlider(id)


}