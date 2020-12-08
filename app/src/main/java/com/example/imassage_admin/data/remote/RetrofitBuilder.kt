package com.example.imassage_admin.data.remote

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder(
        private val client: OkHttpClient
){
    companion object{
        private const val URL = "http://www.paarandco.ir/IMassage/api/"
    }
    operator fun invoke(): Retrofit{
        return Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addCallAdapterFactory(NetworkResponseAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}