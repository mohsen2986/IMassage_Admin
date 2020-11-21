package com.example.imassage_admin.data.remote.api

import com.example.imassage_admin.data.model.AboutUs
import com.example.imassage_admin.data.remote.model.ErrorResponse
import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface ApiInterface{

    @Multipart
    @POST("aboutUs")
    suspend fun uploadImage(
            @Part file: MultipartBody.Part ,
            @Part("description") description: RequestBody
    ): NetworkResponse<AboutUs, ErrorResponse>

    @GET("aboutUs/{id}")
    suspend fun aboutUs(
        @Path("id") id: String
    ): NetworkResponse<AboutUs , ErrorResponse>

    @Multipart
    @POST("aboutUs/{id}")
//    @Headers(
//        "Content-Type: application/x-www-form-urlencoded"
//    )
    suspend fun updateAboutUs(
        @Part file: MultipartBody.Part ,
        @Path("id") id: String ,
        @Query("_method") method: String = "PUT" ,
        @Query("description") description_: String = "this is a test"
    ): NetworkResponse< AboutUs , ErrorResponse>


}