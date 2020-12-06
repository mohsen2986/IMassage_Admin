package com.example.imassage_admin.ui.fragment.ConsultingUsers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.R
import com.example.imassage_admin.databinding.FragmentConsultingUsersBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ConsultingUsersFragment : ScopedFragment()  , KodeinAware{
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: ConsultingUsersViewModelFactory by instance()

    private lateinit var viewModel: ConsultingUsersViewModel
    private lateinit var binding: FragmentConsultingUsersBinding
    private lateinit var navController: NavController


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = FragmentConsultingUsersBinding.inflate(inflater , container  , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(ConsultingUsersViewModel::class.java)
    }

    private fun bindUI() = launch {

    }

}