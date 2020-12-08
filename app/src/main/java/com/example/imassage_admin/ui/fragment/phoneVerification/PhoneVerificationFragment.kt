package com.example.imassage_admin.ui.fragment.phoneVerification

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.R
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.StaticVariables
import com.google.android.material.transition.platform.MaterialElevationScale
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_phone_verificatoin.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class PhoneVerificationFragment: ScopedFragment() , KodeinAware {
    override val kodein: Kodein by closestKodein()
    private  val viewModelFactory: PhoneVerificationViewModelFactory by instance()

    private lateinit var viewModel: PhoneVerificationViewModel
    private lateinit var navController: NavController
    // -- FOR DATA
    private lateinit var verificationType: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_phone_verificatoin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEnterTransitions()
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(PhoneVerificationViewModel::class.java)
        getArgs()
        bindUI()
    }
    private fun bindUI() = launch {
        setExitTransitions()
        fra_phone_verification_next.setOnClickListener{
            hideKeyboard(it)
            sendCode()
        }
        fra_phone_verification_back.setOnClickListener {
            hideKeyboard(it)
            requireActivity().onBackPressed()
        }
        textListeners()
    }
    private fun sendCode() = launch {
        val code: String  = fra_phone_verification_digit_one.text.toString() + fra_phone_verification_digit_two.text.toString() +
                fra_phone_verification_digit_three.text.toString() + fra_phone_verification_digit_four.text.toString()
        when (viewModel.sendVerificationCode(code , verificationType)){
            is NetworkResponse.Success ->{
                navController.navigate(R.id.action_phoneVerificationFragment_to_mainPageFragment ,
                        bundleOf(StaticVariables.SOURCE_FRAGMENT to StaticVariables.VERIFICATION_CODE_FRAGMENT))
            }
        }
    }
    private fun getArgs(){
        verificationType = requireArguments().getString("verification_type").toString()
    }

    private fun textListeners() {
        fra_phone_verification_digit_one.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString().length == 1){
                    fra_phone_verification_digit_two.requestFocus()
                }
            }
        })

        fra_phone_verification_digit_two.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString().length == 1){
                    fra_phone_verification_digit_three.requestFocus()
                }
            }
        })

        fra_phone_verification_digit_three.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString().length == 1){
                    fra_phone_verification_digit_four.requestFocus()
                }
            }
        })
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
        exitTransition = MaterialElevationScale(false).apply {
            duration = 500L
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = 500L
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
                context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}