package com.example.imassage_admin.ui.fragment.question

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.R
import com.example.imassage_admin.databinding.FragmentQuestionBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class QuestionFragment : ScopedFragment() , KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: QuestionViewModelFactory by instance()

    private lateinit var viewModel: QuestionViewModel
    private lateinit var navController: NavController
    private lateinit var binding: FragmentQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionBinding.inflate(inflater , container ,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(QuestionViewModel::class.java)
        bindUI()
        quesiton_txt.setOnClickListener {
            upload()
        }
    }
    private fun bindUI() = launch {
        when(val callback = viewModel.questions()){
            is NetworkResponse.Success ->
                Log.e("Log__" , "${callback.body}")
        }
    }
    private fun upload() =launch {
        viewModel.deleteQuestion("3")
    }

}