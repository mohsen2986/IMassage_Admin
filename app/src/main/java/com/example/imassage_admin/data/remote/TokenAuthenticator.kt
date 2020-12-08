package com.example.imassage_admin.data.remote

import android.util.Log
import com.example.imassage_admin.data.remote.api.ApiInterface
import com.example.imassage_admin.data.repository.TokenRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
        private val tokenRepository: TokenRepository,
        private val apiInterface: ApiInterface
): Authenticator{
    override fun authenticate(route: Route?, response: Response): Request? {
        if(responseCount(response) > 2){
            return null
        }

        val token = tokenRepository.getRefreshToken()
        return runBlocking(Dispatchers.IO){
            when (val callBack = apiInterface.refreshToken(token!!)) {
                is NetworkResponse.Success -> {
                    tokenRepository.saveAccessToken(callBack.body.accessToken)
                    return@runBlocking response.request.newBuilder().header("Authorization" , "Bearer "+ callBack.body.accessToken).build()
                }
                is NetworkResponse.ServerError -> {

                }
            }
            return@runBlocking null
        }
    }
    private fun responseCount(response: Response):Int{
        var count = 1
        var response: Response? = response
        while (response != null){
            count++
            response = response.priorResponse
        }
        return count
    }
}