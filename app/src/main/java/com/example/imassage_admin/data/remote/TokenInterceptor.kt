package com.example.imassage_admin.data.remote

import com.example.imassage_admin.data.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class TokenInterceptor(
        private val client: OkHttpClient,
        private val tokenManager: TokenRepository,
        private val tokenAuthenticator: TokenAuthenticator
){
    operator fun invoke(): OkHttpClient {
        val newBuilder = client.newBuilder().addInterceptor(object: Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                var request = chain.request()
                val builder = request.newBuilder()
                if(tokenManager.getAccessToken()  != "unknown"){
                    builder.addHeader("Authorization" , "Bearer "+ tokenManager.getAccessToken())
                }
                request = builder.build()
                return chain.proceed(request)
            }

        })
            .authenticator(tokenAuthenticator)
            .build()

        return newBuilder
    }
}