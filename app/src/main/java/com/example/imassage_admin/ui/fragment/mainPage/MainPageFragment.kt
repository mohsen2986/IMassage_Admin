package com.example.imassage_admin.ui.fragment.mainPage

import android.Manifest
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsAnimationController
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.R
import com.example.imassage_admin.databinding.FragmentMainPageBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.fragment_main_page.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MainPageFragment : ScopedFragment() , KodeinAware{
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: MainPageViewModelFactory by instance()

    private lateinit var viewModel: MainPageViewModel
    private lateinit var navController: NavController
    private lateinit var binding: FragmentMainPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(layoutInflater , container , false)
        return inflater.inflate(R.layout.fragment_main_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController  = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(MainPageViewModel::class.java)
        bindUI()
        ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE) ,1)
    }
    private fun bindUI() = launch{
        main_txt.setOnClickListener {
            navController.navigate(R.id.action_mainPageFragment_to_aboutUsFragment)
        }
    }
    private fun UIActions(){
        val onClick = View.OnClickListener { view: View? ->

        }
    }

}