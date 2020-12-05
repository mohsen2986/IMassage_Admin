package com.example.imassage_admin.data.remote

import com.example.imassage_admin.data.remote.api.AuthApiInterface
import retrofit2.Retrofit

class CreateAuthApiInterface(
    private val retrofit: Retrofit ,
    private val tokenInterceptor: TokenInterceptor
){
    operator fun invoke(): AuthApiInterface {
        return retrofit.newBuilder().client(tokenInterceptor.invoke())
            .build().create(AuthApiInterface::class.java)
    }
}