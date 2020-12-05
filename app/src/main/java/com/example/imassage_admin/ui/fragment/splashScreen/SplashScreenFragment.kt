package com.example.imassage_admin.ui.fragment.splashScreen

import android.animation.AnimatorInflater
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.R
import com.example.imassage_admin.databinding.FragmentSplashScreenBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.StaticVariables
import com.google.android.material.transition.platform.MaterialSharedAxis
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SplashScreenFragment : ScopedFragment() , KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: SplashScreenViewModelFactory by instance()

    private lateinit var viewModel: SplashScreenViewModel
    private lateinit var binding: FragmentSplashScreenBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        transitionAnimation()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(SplashScreenViewModel::class.java)
        checkLogin()
        bindUI()
    }


    private fun bindUI() = launch{
    }


    // animations
    private fun transitionAnimation() {
        AnimatorInflater.loadAnimator(context, R.animator.splash_screen_animation).apply {
            setTarget(binding.fraSplashScreenImage)
            start()
        }
        AnimatorInflater.loadAnimator(context, R.animator.splash_screen_animation).apply {
            setTarget(binding.fraSplashScreenAppName)
            start()
        }
    }
    private fun checkLogin(){
        GlobalScope.launch(Main) {
            delay(3_500)
            if(viewModel.isLogin) {
                exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z,true).apply {
                    duration = 500L
                }
                navController.navigate(R.id.action_splashScreenFragment_to_mainPageFragment, bundleOf(StaticVariables.SOURCE_FRAGMENT to StaticVariables.SPLASH_FRAGMENT))
            }
            else {
                exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z,true).apply {
                    duration = 500L
                }
                navController.navigate(R.id.action_splashScreenFragment_to_signUpFragment)
            }
        }
    }

}