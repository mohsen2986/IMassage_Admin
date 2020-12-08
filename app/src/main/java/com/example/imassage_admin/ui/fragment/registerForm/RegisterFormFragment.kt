package com.example.imassage_admin.ui.fragment.registerForm

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.R
import com.example.imassage_admin.databinding.FragmentRegisterFormBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_register_form.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class RegisterFormFragment : ScopedFragment() , KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: RegisterFormViewModelFactory by instance()

    private lateinit var viewModel: RegisterFormViewModel
    private lateinit var binding: FragmentRegisterFormBinding
    private lateinit var navController: NavController
    // -- FOR DATA
    private lateinit var name: String
    private lateinit var family: String
    private lateinit var gender: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterFormBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEnterTransitions()
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(RegisterFormViewModel::class.java)
        getArgs()
        bindUI()
    }
    private  fun bindUI() = launch{
        setExitTransitions()
        fra_register_form_next.setOnClickListener{
            registerIntoServer()
            hideKeyboard(it)
        }
        fra_register_form_back.setOnClickListener {
            hideKeyboard(it)
            requireActivity().onBackPressed()
        }
    }
    private fun registerIntoServer() = launch{
        when(val callback = viewModel.register(
                name ,
                family ,
                gender ,
                fra_signUp_second_mobile.text.toString() ,
                fra_signUp_second_password.text.toString() ,
                fra_signUp_second_confirm_password.text.toString())){
            is NetworkResponse.Success ->{
                navController.navigate(R.id.action_registerFormFragment_to_phoneVerificationFragment ,
                        bundleOf("verification_type" to "register")
                )
            }
        }

    }
    private fun getArgs(){
        name = requireArguments().getString("name").toString()
        family = requireArguments().getString("family").toString()
        gender =requireArguments().getString("gender").toString()
    }

    private fun setEnterTransitions() {
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            duration = 500L
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            duration = 500L
        }
    }

    private fun setExitTransitions() {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            duration = 500L
        }

        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            duration = 500L
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
                context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}