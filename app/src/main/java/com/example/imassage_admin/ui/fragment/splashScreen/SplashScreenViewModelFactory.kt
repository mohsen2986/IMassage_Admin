package com.example.imassage_admin.ui.fragment.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imassage_admin.data.repository.DataRepository
import com.example.imassage_admin.data.repository.TokenRepository

class SplashScreenViewModelFactory(
        private val tokenRepository: TokenRepository,
        private val dataRepository: DataRepository
): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashScreenViewModel(tokenRepository , dataRepository) as T
    }
}