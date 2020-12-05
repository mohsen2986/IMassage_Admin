package com.example.imassage_admin.ui.fragment.signUp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.R
import com.example.imassage_admin.ui.base.ScopedFragment
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.imassage.ui.fragment.signUp.SignUpViewModel
import com.imassage.ui.fragment.signUp.SignUpViewModelFactory
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class SignUpFragment : ScopedFragment() , KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: SignUpViewModelFactory by instance()

    private lateinit var viewModel: SignUpViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEnterTransitions()
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(SignUpViewModel::class.java)
        bindUI()
        uiActions()
    }
    private fun bindUI() = launch {
        Log.d("log__" , "enter the page")
    }
    private fun uiActions(){
        fra_signUp_man_woman_group.checkedButtonId
        fra_signUp_next.setOnClickListener{
            hideKeyboard(it)
            setExitTransitions()
            val gender = when(fra_signUp_man_woman_group.checkedButtonId){
                R.id.fra_signUp_man -> "true"
                else -> "false"  // for the other gender Females
            }
            val bundle = bundleOf(
                    "name" to fra_signUp_name.text.toString() ,
                    "family" to fra_signUp_family.text.toString() ,
                    "gender" to gender
            )
            navController.navigate(R.id.action_signUpFragment_to_registerFormFragment , bundle)
        }
        fra_signUp_go_to_login.setOnClickListener {
            hideKeyboard(it)
            setExitTransitions()
            navController.navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


    private fun setEnterTransitions() {
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
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
}