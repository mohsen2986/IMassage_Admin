package com.example.imassage_admin.data.repository

import com.example.imassage_admin.data.model.AboutUs
import com.example.imassage_admin.data.remote.api.ApiInterface
import com.example.imassage_admin.data.remote.model.ErrorResponse
import com.google.gson.JsonObject
import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class DataRepository(
    private val apiInterface: ApiInterface
){
    // ABOUT US
    suspend fun aboutUs() =
        apiInterface.aboutUs("1")

    suspend fun updateAboutUs(file: MultipartBody.Part? , description: String) =
        apiInterface.updateAboutUs(file = file  , id = "1" , description = description)

    suspend fun updateAboutUsDescription(description: String) =
            apiInterface.updateAboutUsDescription(id = "1" , description = description)

    // sliders
    suspend fun getSliders() =
            apiInterface.sliders()

    suspend fun uploadSlider(file: MultipartBody.Part , title: String , description: String) =
            apiInterface.uploadSlider(file , title ,  description)

    suspend fun deleteSlider(id: String) =
            apiInterface.deleteSlider(id)

    // massages
    suspend fun getMassages() =
        apiInterface.massages()

    suspend fun uploadMassage(image: MultipartBody.Part , name: String , cost: String , length: String , description: String) =
        apiInterface.uploadMassage(image , name , cost , length , description)

    suspend fun deleteMassage(id: String) =
        apiInterface.deleteMassage(id)

    // packages
    suspend fun getPackages() =
        apiInterface.packages()

    suspend fun uploadPackage(image: MultipartBody.Part , name: String ,  description: String , cost: String , massageId: String) =
        apiInterface.uploadPackage(image , name , description , cost , massageId)

    suspend fun deletePackage(id: String) =
        apiInterface.deletePackage(id)

    // questions
    suspend fun question() =
            apiInterface.questions()

    suspend fun addQuestion(question: String) =
            apiInterface.uploadQuestion(question)

    suspend fun deleteQuestion(id: String) =
            apiInterface.deleteQuestion(id)
    // users
    suspend fun users(page: Int?) =
            apiInterface.users(page)
}