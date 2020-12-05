package com.example.imassage_admin.ui.fragment.splashScreen

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository
import com.example.imassage_admin.data.repository.TokenRepository

class SplashScreenViewModel(
        private val tokenRepository: TokenRepository,
        private val dataRepository: DataRepository
    ) : ViewModel() {

    val isLogin by lazy {
        tokenRepository.userIsLogin()
    }

}