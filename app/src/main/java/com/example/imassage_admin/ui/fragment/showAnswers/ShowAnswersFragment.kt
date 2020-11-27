package com.example.imassage_admin.ui.fragment.showAnswers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.data.model.Answer
import com.example.imassage_admin.databinding.FragmentShowAnswersBinding
import com.example.imassage_admin.ui.adapter.ryclerView.RecyclerAdapter
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.StaticVariables
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_show_answers.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ShowAnswersFragment : ScopedFragment() , KodeinAware{
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: ShowAnswersViewModelFactory by instance()

    private lateinit var viewModel: ShowAnswersViewModel
    private lateinit var binding: FragmentShowAnswersBinding
    private lateinit var navController: NavController

    // -- FOR DATA
    private lateinit var formId: String
    private lateinit var adapter: RecyclerAdapter<Answer>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = FragmentShowAnswersBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(ShowAnswersViewModel::class.java)
        getArgument()
        initAdapter()
        bindAdapter()
        bindUI()
    }

    private fun bindUI() = launch {
        when(val callback = viewModel.answer(filledForm = formId)){
            is NetworkResponse.Success ->{
                adapter.datas = callback.body.answers
            }
        }
    }
    private fun getArgument(){
        formId = requireArguments().getString(StaticVariables.FORM_ID).toString()
    }
    private fun initAdapter(){
        adapter = RecyclerAdapter()
    }
    private fun bindAdapter(){
        fra_answer_recycler.adapter = adapter
    }

}