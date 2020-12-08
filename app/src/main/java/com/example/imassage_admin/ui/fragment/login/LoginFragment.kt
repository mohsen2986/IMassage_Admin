package com.example.imassage_admin.ui.fragment.login

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.R
import com.example.imassage_admin.databinding.FragmentLoginBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.ShowStatus
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class LoginFragment : ScopedFragment() , KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: LoginViewModelFactory by instance()

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding : FragmentLoginBinding
    private lateinit var navController: NavController
    private val status = ShowStatus()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        setEnterTransitions()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(LoginViewModel::class.java)
        bindUI()
        onClickListeners()
    }

    private fun bindUI() = launch {

    }
    private fun onClickListeners(){
        fra_login_back.setOnClickListener{
            exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
                duration = 500L
            }
            reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
                duration = 500L
            }
            hideKeyboard(it)
            requireActivity().onBackPressed()
        }
        fra_login_LoginButton.setOnClickListener{
            status.showProgress(binding.fraLoginShowStatus,it as Button)
            hideKeyboard(it)
            exitTransition =  MaterialSharedAxis(MaterialSharedAxis.X,true).apply {
                duration = 500L
            }
            reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X,false).apply {
                duration = 500L
            }
            loginIntoServer()
        }
    }
    private fun loginIntoServer() = launch {
        val phone = fra_login_mobile.text.toString()
        val password = fra_login_password.text.toString()
        when(val calback = viewModel.login(phone , password)){
            is NetworkResponse.Success -> {
                status.showSuccess(binding.fraLoginShowStatus)
                navController.navigate(R.id.action_loginFragment_to_phoneVerificationFragment ,
                        bundleOf("verification_type" to "login")
                )
            }
        }
    }


    private fun hideKeyboard(view: View) {
        val inputMethodManager =
                context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun setEnterTransitions() {
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            duration = 500L
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            duration = 500L
        }
    }
}