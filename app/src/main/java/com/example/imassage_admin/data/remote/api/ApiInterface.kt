package com.example.imassage_admin.data.remote.api

import com.example.imassage_admin.data.model.AboutUs
import com.example.imassage_admin.data.model.Boarder
import com.example.imassage_admin.data.model.Question
import com.example.imassage_admin.data.remote.model.*
import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface ApiInterface{

    @Multipart
    @POST("aboutUs")
    suspend fun uploadImage(
            @Part file: MultipartBody.Part? ,
            @Part("description") description: RequestBody
    ): NetworkResponse<AboutUs, ErrorResponse>

    @GET("aboutUs/{id}")
    suspend fun aboutUs(
        @Path("id") id: String
    ): NetworkResponse<AboutUs , ErrorResponse>

    @Multipart
    @POST("aboutUs/{id}")
    suspend fun updateAboutUs(
        @Part file: MultipartBody.Part? ,
        @Path("id") id: String ,
        @Query("_method") method: String = "PUT" ,
        @Query("description") description: String?
    ): NetworkResponse< AboutUs , ErrorResponse>

    @POST("aboutUs/{id}")
    suspend fun updateAboutUsDescription(
            @Path("id") id: String ,
            @Query("_method") method: String = "PUT" ,
            @Query("description") description: String
    ): NetworkResponse< AboutUs , ErrorResponse>

    // sliders
    @GET("boarder")
    suspend fun sliders(
    ): NetworkResponse< BoarderResponse , ErrorResponse>

    @Multipart
    @POST("boarder")
    suspend fun uploadSlider(
            @Part file: MultipartBody.Part ,
            @Query("title") title: String ,
            @Query("description") description: String
    ): NetworkResponse< Boarder , ErrorResponse>

    @DELETE("boarder/{id}")
    suspend fun deleteSlider(
            @Path("id") id: String
    ): NetworkResponse<Boarder , ErrorResponse>

    // massage
    @GET("massage")
    suspend fun massages(
    ): NetworkResponse<MassageResponse, ErrorResponse>

    @Multipart
    @POST("massage")
    suspend fun uploadMassage(
            @Part image: MultipartBody.Part ,
            @Query("name") name: String ,
            @Query("cost") cost: String ,
            @Query("length") length: String ,
            @Query("description") description: String
    ):NetworkResponse<MassageResponse ,ErrorResponse>

    @DELETE("massage/{id}")
    suspend fun deleteMassage(
            @Path("id") id: String
    ): NetworkResponse<MassageResponse , ErrorResponse>

    // packages
    @GET("packages")
    suspend fun packages(
    ): NetworkResponse<PackageResponse, ErrorResponse>

    @Multipart
    @POST("packages")
    suspend fun uploadPackage(
        @Part image: MultipartBody.Part ,
        @Query("name") name: String ,
        @Query("description") description: String ,
        @Query("cost") cost: String ,
        @Query("massage_id") massageId: String
    ): NetworkResponse<PackageResponse ,ErrorResponse>

    @DELETE("packages/{id}")
    suspend fun deletePackage(
        @Path("id") id: String
    ): NetworkResponse<PackageResponse , ErrorResponse>

    // questions
    @GET("questions")
    suspend fun questions(
    ): NetworkResponse<QuestionResponse, ErrorResponse>

    @POST("question")
    suspend fun uploadQuestion(
            @Query("question") question: String ,
            @Query("question_type_id") questionTypeId: String = "1" ,
            @Query("form_id") formId: String = "1"
    ): NetworkResponse< Question , ErrorResponse>

    @DELETE("question/{id}")
    suspend fun deleteQuestion(
            @Path("id") id: String
    ): NetworkResponse<Question , ErrorResponse>

    // Users
    @GET("users")
    suspend fun users(
            @Query("page") page: Int?
    ): NetworkResponse< UserResponse , ErrorResponse>

}