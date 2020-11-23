package com.example.imassage_admin.ui.fragment.massage

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository
import okhttp3.MultipartBody

class MassageViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    suspend fun massages() =
        dataRepository.getMassages()

    suspend fun uploadMassage(image: MultipartBody.Part , name: String , cost: String , length: String , description: String) =
        dataRepository.uploadMassage(image , name , cost , length , description)

    suspend fun deleteMassage(id: String) =
        dataRepository.deleteMassage(id)
}