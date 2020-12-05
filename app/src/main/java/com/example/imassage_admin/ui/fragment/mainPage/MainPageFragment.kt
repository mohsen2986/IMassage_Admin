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
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.R
import com.example.imassage_admin.databinding.FragmentMainPageBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.OnCLickHandler
import com.example.imassage_admin.ui.utils.StaticVariables
import com.haroldadmin.cnradapter.NetworkResponse
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController  = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(MainPageViewModel::class.java)
        bindUI()
        UIActions()
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE) ,1)
    }
    private fun bindUI() = launch{
        when(val callback = viewModel.reservedOrders()){
            is NetworkResponse.Success -> {
                if(callback.body.data.isNotEmpty())
                    binding.order = callback.body.data[0]
            }
        }
    }
    private fun UIActions(){
        binding.onClick = object: OnCLickHandler<Nothing>{
            override fun onClickItem(element: Nothing) {}
            override fun onClickView(view: View, element: Nothing) {}
            override fun onClick(view: View) {
                when(view){
                    fra_main_page_aboutUs ->
                        navController.navigate(R.id.action_mainPageFragment_to_aboutUsFragment)
                    fra_main_page_slider ->
                        navController.navigate(R.id.action_mainPageFragment_to_sliderFragment)
                    fra_main_page_package ->
                        navController.navigate(R.id.action_mainPageFragment_to_packagesFragment)
                    fra_main_page_massage ->
                        navController.navigate(R.id.action_mainPageFragment_to_massageFragment)
                    fra_main_page_payments ->
                        navController.navigate(R.id.action_mainPageFragment_to_historyFragment , bundleOf(StaticVariables.RESERVE_TYPE to StaticVariables.HISTORY))
                    fra_main_page_config_time ->
                        navController.navigate(R.id.action_mainPageFragment_to_configFragment)
                    fra_main_page_config_date ->
                        navController.navigate(R.id.action_mainPageFragment_to_configTimesFragment)
                    fra_main_page_users ->
                        navController.navigate(R.id.action_mainPageFragment_to_usersFragment)
                    fra_main_page_question ->
                        navController.navigate(R.id.action_mainPageFragment_to_questionFragment)
                    fra_main_page_offer_token ->
                        navController.navigate(R.id.action_mainPageFragment_to_offerFragment)
                    fra_main_page_reserved ->
                        navController.navigate(R.id.action_mainPageFragment_to_historyFragment , bundleOf(StaticVariables.RESERVE_TYPE to StaticVariables.RESERVE_TIME))
                }
            }


        }
    }

}