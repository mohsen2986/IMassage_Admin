package com.example.imassage_admin.data.remote

import com.example.imassage_admin.data.remote.api.ApiInterface
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service(
){
    operator fun invoke() : ApiInterface {
        return Retrofit.Builder()
            .baseUrl("http://www.paarandco.ir/IMassage/api/")
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder()
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
                    .addNetworkInterceptor(StethoInterceptor())
                .build())
            .build()
            .create(ApiInterface::class.java)
    }
}