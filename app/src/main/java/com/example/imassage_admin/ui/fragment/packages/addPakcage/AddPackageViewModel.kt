package com.example.imassage_admin.ui.fragment.packages.addPakcage

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository
import okhttp3.MultipartBody

class AddPackageViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    suspend fun uploadPackages(image: MultipartBody.Part, name: String, description: String, cost: String, massageId: String , length: String) =
        dataRepository.uploadPackage(image , name , description , cost , massageId , length)

    suspend fun massages() =
            dataRepository.getMassages()


}