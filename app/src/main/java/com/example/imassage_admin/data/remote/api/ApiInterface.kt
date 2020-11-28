package com.example.imassage_admin.data.remote.api

import com.example.imassage_admin.data.remote.model.AccessToken
import com.example.imassage_admin.data.remote.model.ErrorResponse
import com.example.imassage_admin.data.remote.model.SmsVerificationResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface{
    // register
    @POST("register")
    @FormUrlEncoded
    suspend fun register(
        @Field("name")
        name: String ,
        @Field("family")
        family: String ,
        @Field("password")
        password: String ,
        @Field("password_confirmation")
        passwordConfirmation: String ,
        @Field("phone")
        phone: String ,
        @Field("gender")
        gender: Boolean
    ): NetworkResponse<SmsVerificationResponse, ErrorResponse>

    @POST("registerVerify")
    @FormUrlEncoded
    suspend fun registerVerification(
        @Field("token")
        token: String ,
        @Field("code")
        code: String,
        @Field("password")
        password: String
    ): NetworkResponse<AccessToken, ErrorResponse>

    // login
    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("phone")
        phone: String ,
        @Field("password")
        password: String
    ): NetworkResponse<SmsVerificationResponse , ErrorResponse>

    @POST("loginVerify")
    @FormUrlEncoded
    suspend fun loginVerification(
        @Field("token")
        token: String,
        @Field("code")
        code: String ,
        @Field("password")
        password: String
    ): NetworkResponse<AccessToken , ErrorResponse>

    // logout
    @POST("logout")
    suspend fun logOut(
    ): NetworkResponse< Nothing , ErrorResponse>

    // refresh token
    @POST("refresh")
    @FormUrlEncoded
    suspend fun refreshToken(
        @Field("refresh_token")
        refreshToke: String
    ): NetworkResponse<AccessToken , ErrorResponse>
}