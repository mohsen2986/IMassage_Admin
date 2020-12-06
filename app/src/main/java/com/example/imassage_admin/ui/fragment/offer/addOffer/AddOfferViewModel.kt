package com.example.imassage_admin.ui.fragment.offer.addOffer

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository

class AddOfferViewModel(
        private val dataRepository: DataRepository
) : ViewModel() {

    suspend fun createOffer(number: String , perecent: String , massageId: String , startTime: String , expireTime: String) =
            dataRepository.createOffer(number , perecent , massageId , startTime , expireTime)

    suspend fun massages() =
            dataRepository.getMassages()
}