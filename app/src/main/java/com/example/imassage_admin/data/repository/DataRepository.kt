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

    // configs
    suspend fun configs()=
            apiInterface.configs()
    suspend fun updateConfigs(
        h8: String ,
        h8_gender: String ,
        h9: String ,
        h9_gender: String ,
        h10: String ,
        h10_gender: String ,
        h11: String ,
        h11_gender: String ,
        h12: String ,
        h12_gender: String ,
        h13: String ,
        h13_gender: String ,
        h14: String ,
        h14_gender: String ,
        h15: String ,
        h15_gender: String ,
        h16: String ,
        h16_gender: String ,
        h17: String ,
        h17_gender: String ,
        h18: String ,
        h18_gender: String ,
        h19: String ,
        h19_gender: String ,
        h20: String ,
        h20_gender: String ,
        h21: String ,
        h21_gender: String ,
        h22: String ,
        h22_gender: String ,
        d1: String ,
        d2: String ,
        d3: String ,
        d4: String ,
        d5: String ,
        d6: String ,
        d7: String ,
        closed_days: String ,
        open_days: String
    ) = apiInterface.updateConfigs(
            h8 ,
                    h8_gender ,
                    h9 ,
                    h9_gender ,
                    h10,
                    h10_gender,
                    h11,
                    h11_gender,
                    h12,
                    h12_gender,
                    h13,
                    h13_gender,
                    h14,
                    h14_gender,
                    h15,
                    h15_gender,
                    h16,
                    h16_gender,
                    h17,
                    h17_gender,
                    h18,
                    h18_gender,
                    h19,
                    h19_gender,
                    h20,
                    h20_gender,
                    h21,
                    h21_gender,
                    h22,
                    h22_gender,
                    d1,
                    d2,
                    d3,
                    d4,
                    d5,
                    d6,
                    d7,
                    closed_days,
                    open_days
    )

    // order
    suspend fun orders(page: Int?) =
            apiInterface.order(page)

    // answers
    suspend fun answers(filledForm: String) =
            apiInterface.answers(filledForm)
}