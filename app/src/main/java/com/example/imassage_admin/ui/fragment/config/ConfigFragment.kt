package com.example.imassage_admin.ui.fragment.config

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.databinding.FragmentConfigBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ConfigFragment : ScopedFragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: ConfigViewModelFactory by instance()

    private lateinit var viewModel: ConfigViewModel
    private lateinit var navController: NavController
    private lateinit var binding: FragmentConfigBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfigBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(ConfigViewModel::class.java)
        bindUI()
    }
    private fun bindUI() = launch{
        when(val callback = viewModel.config()){
            is NetworkResponse.Success ->{
                Log.e("Log__" , "${callback.body}")
            }
        }
    }


}