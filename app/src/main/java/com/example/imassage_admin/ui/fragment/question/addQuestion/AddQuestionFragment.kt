package com.example.imassage_admin.ui.fragment.question.addQuestion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.R
import com.example.imassage_admin.databinding.FragmentAddQuestionBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.OnCLickHandler
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_add_question.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class AddQuestionFragment : ScopedFragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: AddQuestionViewModelFactory by instance()

    private lateinit var viewModel: AddQuestionViewModel
    private lateinit var navController: NavController
    private lateinit var binding: FragmentAddQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddQuestionBinding.inflate(layoutInflater , container ,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(AddQuestionViewModel::class.java)
        bindUI()
        uiActions()
    }
    private fun bindUI() = launch {

    }
    private fun uiActions(){
        binding.onClick = object:OnCLickHandler<Nothing>{
            override fun onClickItem(element: Nothing) {}
            override fun onClickView(view: View, element: Nothing) {}
            override fun onClick(view: View) {
                when(view){
                    fra_add_question_back ->
                        requireActivity().onBackPressed()
                    fra_add_question_submit -> {
                        if (fra_add_question_question.text?.isNotEmpty()!!)
                            sendQuestion()
                        else
                            Toast.makeText(context , "متن را وارد گنید" , Toast.LENGTH_SHORT).show()
                    }


                }
            }


        }
    }
    private fun sendQuestion() = launch {
        when(val callback = viewModel.addQuestion(fra_add_question_question.text.toString())){
            is NetworkResponse.Success ->
                Toast.makeText(context , "سوال با موفقیت بار گذاری شد." , Toast.LENGTH_SHORT).show()
        }
    }

}