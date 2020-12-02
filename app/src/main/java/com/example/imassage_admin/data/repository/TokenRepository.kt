package com.example.imassage_admin.data.repository

import com.imassage.data.database.sharedPreferences.Preferences

class TokenRepository(
        private val preferences: Preferences
){
    companion object{
        private const val PHONE = "PHONE"
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
        private const val LOGIN = "LOGIN"
        private const val ISLOGINED = "ISLOGINED"
        private const val NOTLOGIN = "NOTLOGIN"
    }
    // phone number
    fun savePhone(phone: String) =
            preferences.savePref(PHONE , phone)
    fun getPhone() =
            preferences.getPref(PHONE)
    // access token
    fun saveAccessToken(accessToken: String) =
            preferences.savePref(ACCESS_TOKEN , accessToken)
    fun getAccessToken() =
            preferences.getPref(ACCESS_TOKEN) ?: "unknown"
    // refresh token
    fun saveRefreshToken(refreshToken: String) =
            preferences.savePref(REFRESH_TOKEN , refreshToken)
    fun getRefreshToken() =
            preferences.getPref(REFRESH_TOKEN) ?: "unknown"
    // check user is login
    fun userIsLogin():Boolean =
            (preferences.getPref(LOGIN) ?: NOTLOGIN) == ISLOGINED
    fun userLogin() =
            preferences.savePref(LOGIN , ISLOGINED)
    fun userLogOut() =
            preferences.savePref(LOGIN, NOTLOGIN)


}