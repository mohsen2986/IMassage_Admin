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

    suspend fun aboutUs() =
        apiInterface.aboutUs("1")

    suspend fun updateAboutUs(file: MultipartBody.Part , description: RequestBody) =
        apiInterface.updateAboutUs(file = file  , id = "1")

//    suspend fun updateAboutUs(file: MultipartBody.Part , description: RequestBody): NetworkResponse<AboutUs, ErrorResponse> {
//        val jobj = JSONObject()
//        jobj.put("description" , "test")
//        val requestBody = jobj.toString().toRequestBody("application/json".toMediaTypeOrNull())
//
//        return apiInterface.updateAboutUs1( requestBody , id = "1")
//    }

}