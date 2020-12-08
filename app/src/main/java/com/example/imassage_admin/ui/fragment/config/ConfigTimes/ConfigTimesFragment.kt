package com.example.imassage_admin.ui.fragment.config.ConfigTimes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.R
import com.example.imassage_admin.data.model.Config
import com.example.imassage_admin.databinding.FragmentConfigBinding
import com.example.imassage_admin.databinding.FragmentConfigTimesBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.OnCLickHandler
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_config.*
import kotlinx.android.synthetic.main.fragment_config_times.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ConfigTimesFragment : ScopedFragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: ConfigTimesViewModelFactory by instance()

    private lateinit var viewModel: ConfigTimesViewModel
    private lateinit var navController: NavController
    private lateinit var binding: FragmentConfigTimesBinding

    // FOR DATA
    private lateinit var config: Config

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfigTimesBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(ConfigTimesViewModel::class.java)
        uiActions()
        bindUI()
    }
    private fun bindUI() = launch{
        when(val callback = viewModel.config()){
            is NetworkResponse.Success ->{
                config = callback.body
                binding.config = callback.body
            }
        }
    }
    private fun uiActions(){
        binding.onClick = object: OnCLickHandler<Nothing>{
            override fun onClickItem(element: Nothing) {}
            override fun onClickView(view: View, element: Nothing) {}
            override fun onClick(view: View) {
                when(view){
                    fra_config_date_back ->
                        requireActivity().onBackPressed()
                    fra_config_date_submit ->
                        updateConfig()
                }
            }
        }
    }
    private fun updateConfig() = launch {
        when(val callback = viewModel.updateConfigs(
                config.h1 ,
                config.h1Gender ,
                config.h2 ,
                config.h2Gender ,
                config.h3 ,
                config.h3Gender ,
                config.h4 ,
                config.h4Gender ,
                config.h5 ,
                config.h5Gender ,
                config.h6 ,
                config.h6Gender ,
                config.h7 ,
                config.h7Gender ,
                config.h8 ,
                config.h8Gender ,
                config.h9 ,
                config.h9Gender ,
                config.h10 ,
                config.h10Gender ,
                config.h11 ,
                config.h11Gender ,
                config.h12 ,
                config.h12Gender ,
                config.h13 ,
                config.h13Gender ,
                config.h14 ,
                config.h14Gender ,
                config.h15 ,
                config.h15Gender ,
                config.h16 ,
                config.h16Gender ,
                config.h17 ,
                config.h17Gender ,
                config.h18 ,
                config.h18Gender ,
                config.h19 ,
                config.h19Gender ,
                config.h20 ,
                config.h20Gender ,
                config.h21 ,
                config.h21Gender ,
                config.h22 ,
                config.h22Gender ,
                config.h23 ,
                config.h23Gender ,
                config.h24 ,
                config.h24Gender ,
                if(fra_config_date_active_0.isChecked) "OPEN" else "CLOSE" ,
                if(fra_config_date_active_1.isChecked) "OPEN" else "CLOSE" ,
                if(fra_config_date_active_2.isChecked) "OPEN" else "CLOSE" ,
                if(fra_config_date_active_3.isChecked) "OPEN" else "CLOSE" ,
                if(fra_config_date_active_4.isChecked) "OPEN" else "CLOSE" ,
                if(fra_config_date_active_5.isChecked) "OPEN" else "CLOSE" ,
                if(fra_config_date_active_6.isChecked) "OPEN" else "CLOSE" ,
                fra_config_date_disable_days.text.toString() ,
                fra_config_date_enable_days.text.toString()
        )){
             is NetworkResponse.Success ->
                 Toast.makeText(context, "زمان در سیستم ثبت شد." , Toast.LENGTH_LONG).show()
        }
    }
}