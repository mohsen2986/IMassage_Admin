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
import com.example.imassage_admin.data.model.Question
import com.example.imassage_admin.databinding.FragmentQuestionBinding
import com.example.imassage_admin.ui.adapter.ryclerView.RecyclerAdapter
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.OnCLickHandler
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_add_question.*
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

    // -- FOR DATA
    private lateinit var adapter: RecyclerAdapter<Question>

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
        initAdapter()
        bindAdapter()
        bindUI()
        uiActions()
    }
    private fun bindUI() = launch {
        when(val callback = viewModel.questions()){
            is NetworkResponse.Success ->
                adapter.datas = callback.body.datas
        }
    }
    private fun deleteQuestion(id: String) =launch {
        viewModel.deleteQuestion(id)
    }
    private fun uiActions(){
        binding.onClick = object: OnCLickHandler<Nothing>{
            override fun onClickItem(element: Nothing) {}
            override fun onClickView(view: View, element: Nothing) {}
            override fun onClick(view: View) {
                when(view){
                    fra_question_back ->
                        requireActivity().onBackPressed()
                    fra_question_add_question ->
                        navController.navigate(R.id.action_questionFragment_to_addQuestionFragment)
                }
            }
        }
    }
    private fun initAdapter(){
        adapter = RecyclerAdapter()
        adapter.onClickHandler = object: OnCLickHandler<Question>{
            override fun onClickItem(element: Question) {
                deleteQuestion(element.questionId)
            }

            override fun onClick(view: View) {}
            override fun onClickView(view: View, element: Question) {}
        }
    }
    private fun bindAdapter(){
        fra_question_recycler.adapter = adapter
    }

}