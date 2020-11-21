package com.example.imassage_admin.ui.fragment.aboutUs

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AboutUsViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    suspend fun aboutUs() =
        dataRepository.aboutUs()

    suspend fun aboutUsUpdate(file: MultipartBody.Part, description: RequestBody) =
        dataRepository.updateAboutUs(file , description)

}