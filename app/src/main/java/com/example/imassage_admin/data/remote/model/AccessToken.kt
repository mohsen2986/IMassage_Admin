package com.example.imassage_admin.data.remote.model

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("token_type")
    val tokenType: String = "Bearer",
    @SerializedName("expired_in")
    val  expiredIn: Int = 0,
    @SerializedName("access_token")
    val accessToken: String  = "unknown",
    @SerializedName("refresh_token")
    val refreshToken: String ="unknown"
)