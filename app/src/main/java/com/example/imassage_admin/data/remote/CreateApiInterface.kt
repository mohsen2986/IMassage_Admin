package com.example.imassage_admin.data.remote

import com.example.imassage_admin.data.remote.api.ApiInterface
import retrofit2.Retrofit

class CreateApiInterface(
    private val retrofit: Retrofit
){
    operator fun invoke(): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}