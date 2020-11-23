package com.example.imassage_admin.ui.fragment.packages

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository
import okhttp3.MultipartBody

class PackagesViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    suspend fun packages() =
        dataRepository.getPackages()

    suspend fun uploadPackages(image: MultipartBody.Part , name: String ,  description: String , cost: String , massageId: String ) =
        dataRepository.uploadPackage(image , name , description , cost , massageId)

    suspend fun deletePackage(id: String) =
        dataRepository.deletePackage(id)
}