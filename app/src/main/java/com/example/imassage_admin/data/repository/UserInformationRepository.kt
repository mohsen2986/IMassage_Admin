package com.example.imassage_admin.data.repository

import android.util.Log
import com.example.imassage_admin.data.remote.api.ApiInterface
import com.example.imassage_admin.data.remote.model.AccessToken
import com.example.imassage_admin.data.remote.model.ErrorResponse
import com.example.imassage_admin.data.remote.model.SmsVerificationResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserInformationRepository(
        private val tokenRepository: TokenRepository ,
        private val apiInterface: ApiInterface
){
    companion object{
        private lateinit var smsToken: String
        private lateinit var password: String
    }
    suspend fun register(name: String , family: String, phone: String  , gender: String , password: String, passwordConfirm: String): NetworkResponse<SmsVerificationResponse, ErrorResponse> {
        return withContext(IO) {
            val callback = apiInterface.register(name, family , password, passwordConfirm , phone , gender == "true")
            Log.d("log__" , "the call is: $callback")
            when (callback){
                is NetworkResponse.Success -> {
                    Companion.smsToken = callback.body.token
                    Companion.password = password
                }
            }
            return@withContext callback
        }
    }
    suspend fun registerVerifySms(code: String): NetworkResponse<AccessToken, ErrorResponse> {
        Log.d("log__" , "name $smsToken" )
        Log.d("log__" , "pass $password" )
        Log.d("log__" , "name $code" )
        return withContext(IO){
            val callback = apiInterface.registerVerification(smsToken , code, password)
            when(callback){
                is NetworkResponse.Success -> {
                    tokenRepository.saveAccessToken(callback.body.accessToken)
                    tokenRepository.saveRefreshToken(callback.body.refreshToken)
                    tokenRepository.userLogin()
                }
            }
            return@withContext callback
        }
    }
    suspend fun login(phone: String , password: String) =
        withContext(IO){
            val callback = apiInterface.login(phone , password)
            when(callback){
                is NetworkResponse.Success ->{
                    Companion.smsToken = callback.body.token
                    Companion.password = password
                }
            }
            return@withContext callback
        }
    suspend fun loginVerifySms(code: String) =
            withContext(IO){
                val callback = apiInterface.loginVerification(smsToken , code , password)
                when(callback){
                    is NetworkResponse.Success -> {
                        tokenRepository.saveAccessToken(callback.body.accessToken)
                        tokenRepository.saveRefreshToken(callback.body.refreshToken)
                        tokenRepository.userLogin()
                    }
                }
                return@withContext callback
            }

}