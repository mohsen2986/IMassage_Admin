package com.example.imassage_admin.data.remote

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class Client(
){
    operator fun invoke(): OkHttpClient{
        val client : OkHttpClient by lazy {
            val builder = OkHttpClient.Builder()
                    .addInterceptor(object: Interceptor{
                        override fun intercept(chain: Interceptor.Chain): Response {
                            var request = chain.request()

                            val builder = request.newBuilder()
                                    .addHeader("Accept", "application/json")
                                    .addHeader("Connection", "close")

                            request = builder.build()
                            return chain.proceed(request)
                        }
                    })
            builder.addNetworkInterceptor(StethoInterceptor())
            builder.build()
        }
        return client
    }
}