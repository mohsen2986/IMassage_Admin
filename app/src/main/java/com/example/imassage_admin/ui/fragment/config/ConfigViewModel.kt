package com.example.imassage_admin.ui.fragment.config

import androidx.lifecycle.ViewModel
import com.example.imassage_admin.data.repository.DataRepository

class ConfigViewModel(
        private val dataRepository: DataRepository
) : ViewModel() {

    suspend fun updateConfigs(
            h0: String ,
            h0_gender: String ,
            h1: String ,
            h1_gender: String ,
            h2: String ,
            h2_gender: String ,
            h3: String ,
            h3_gender: String ,
            h4: String ,
            h4_gender: String ,
            h5: String ,
            h5_gender: String ,
            h6: String ,
            h6_gender: String ,
            h7: String ,
            h7_gender: String ,
            h8: String ,
            h8_gender: String ,
            h9: String ,
            h9_gender: String ,
            h10: String ,
            h10_gender: String ,
            h11: String ,
            h11_gender: String ,
            h12: String ,
            h12_gender: String ,
            h13: String ,
            h13_gender: String ,
            h14: String ,
            h14_gender: String ,
            h15: String ,
            h15_gender: String ,
            h16: String ,
            h16_gender: String ,
            h17: String ,
            h17_gender: String ,
            h18: String ,
            h18_gender: String ,
            h19: String ,
            h19_gender: String ,
            h20: String ,
            h20_gender: String ,
            h21: String ,
            h21_gender: String ,
            h22: String ,
            h22_gender: String ,
            h23: String ,
            h23_gender: String ,
            d1: String ,
            d2: String ,
            d3: String ,
            d4: String ,
            d5: String ,
            d6: String ,
            d7: String ,
            closed_days: String ,
            open_days: String
    ) = dataRepository.updateConfigs(
            h0,
            h0_gender,
            h1,
            h1_gender,
            h2,
            h2_gender,
            h3,
            h3_gender,
            h4,
            h4_gender,
            h5,
            h5_gender,
            h6,
            h6_gender,
            h7,
            h7_gender,
            h8 ,
            h8_gender ,
            h9 ,
            h9_gender ,
            h10,
            h10_gender,
            h11,
            h11_gender,
            h12,
            h12_gender,
            h13,
            h13_gender,
            h14,
            h14_gender,
            h15,
            h15_gender,
            h16,
            h16_gender,
            h17,
            h17_gender,
            h18,
            h18_gender,
            h19,
            h19_gender,
            h20,
            h20_gender,
            h21,
            h21_gender,
            h22,
            h22_gender,
            h23,
            h23_gender,
            d1,
            d2,
            d3,
            d4,
            d5,
            d6,
            d7,
            closed_days,
            open_days
    )

    suspend fun config() =
            dataRepository.configs()


}