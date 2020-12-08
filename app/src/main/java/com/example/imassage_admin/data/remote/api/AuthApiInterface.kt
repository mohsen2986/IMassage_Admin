package com.example.imassage_admin.data.remote.api

import com.example.imassage_admin.data.model.*
import com.example.imassage_admin.data.remote.model.*
import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface AuthApiInterface{

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
            @Query("massage_id") massageId: String ,
            @Query("length") length: String
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

    // configs
    @GET("configs")
    suspend fun configs(
    ): NetworkResponse<Config, ErrorResponse>

    @POST("configs")
    suspend fun updateConfigs(
            @Query("h8") h8: String ,
            @Query("h8_gender") h8_gender: String ,
            @Query("h9") h9: String ,
            @Query("h9_gender") h9_gender: String ,
            @Query("h10") h10: String ,
            @Query("h10_gender") h10_gender: String ,
            @Query("h11") h11: String ,
            @Query("h11_gender") h11_gender: String ,
            @Query("h12") h12: String ,
            @Query("h12_gender") h12_gender: String ,
            @Query("h13") h13: String ,
            @Query("h13_gender") h13_gender: String ,
            @Query("h14") h14: String ,
            @Query("h14_gender") h14_gender: String ,
            @Query("h15") h15: String ,
            @Query("h15_gender") h15_gender: String ,
            @Query("h16") h16: String ,
            @Query("h16_gender") h16_gender: String ,
            @Query("h17") h17: String ,
            @Query("h17_gender") h17_gender: String ,
            @Query("h18") h18: String ,
            @Query("h18_gender") h18_gender: String ,
            @Query("h19") h19: String ,
            @Query("h19_gender") h19_gender: String ,
            @Query("h20") h20: String ,
            @Query("h20_gender") h20_gender: String ,
            @Query("h21") h21: String ,
            @Query("h21_gender") h21_gender: String ,
            @Query("h22") h22: String ,
            @Query("h22_gender") h22_gender: String ,
            @Query("d1") d1: String ,
            @Query("d2") d2: String ,
            @Query("d3") d3: String ,
            @Query("d4") d4: String ,
            @Query("d5") d5: String ,
            @Query("d6") d6: String ,
            @Query("d7") d7: String ,
            @Query("closed_days") closed_days: String ,
            @Query("open_days") open_days: String
    ): NetworkResponse<Config , ErrorResponse>

    // order
    @GET("order")
    suspend fun order(
            @Query("page") page: Int? ,
            @Query("user") user: String?
    ): NetworkResponse< OrderResponse , ErrorResponse>

    // answers
    @POST("answers")
    suspend fun answers(
            @Query("filled_form")
            filledForm: String
    ): NetworkResponse< AnswerResponse , ErrorResponse>

    // create offer
    @POST("offer")
    suspend fun createOffer(
            @Query("number") number: String ,
            @Query("percent") percent: String ,
            @Query("massage") massageId: String,
            @Query("start_date") startTime: String ,
            @Query("expire_date") expireTime: String
    ): NetworkResponse< OfferResponse , ErrorResponse>

    @GET("offer")
    suspend fun offers(
            @Query("page") page: Int?
    ): NetworkResponse< OfferResponsePagination , ErrorResponse>

    @DELETE("offer/{id}")
    suspend fun deleteOffer(
            @Path("id") id: String
    ): NetworkResponse<Offer,ErrorResponse >

    //get orders
    @POST("reservedOrders")
    suspend fun reservedOrders(
            @Query("page") page: Int?
    ): NetworkResponse< OrderResponse , ErrorResponse>

    // get consulting
    @POST("consultingUsers")
    suspend fun getConsulting(
    ): NetworkResponse< UsersResponse , ErrorResponse>

    @POST("setConsultingUser")
    suspend fun setConsultingUser(
        @Query("user") userId: String
    ): NetworkResponse< Unit , ErrorResponse>

    // download users as PDF
    @GET
    suspend fun downloadUsers(
            @Url url:String = "http://www.paarandco.ir/IMassage/api/getPdf"
    ): NetworkResponse<ResponseBody, ResponseBody>
}