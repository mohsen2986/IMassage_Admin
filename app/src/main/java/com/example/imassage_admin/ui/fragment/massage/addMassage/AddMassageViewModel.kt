package com.example.imassage_admin.ui.fragment.massage.addMassage

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository
import okhttp3.MultipartBody

class AddMassageViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    suspend fun uploadMassage(image: MultipartBody.Part, name: String, cost: String, length: String, description: String) =
        dataRepository.uploadMassage(image , name , cost , length , description)

}