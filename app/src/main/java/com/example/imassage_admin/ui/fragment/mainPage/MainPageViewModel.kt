package com.example.imassage_admin.ui.fragment.mainPage

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository

class MainPageViewModel(
        private val dataRepository: DataRepository
) : ViewModel() {
    suspend fun reservedOrders() =
            dataRepository.reservedOrders(1)
}