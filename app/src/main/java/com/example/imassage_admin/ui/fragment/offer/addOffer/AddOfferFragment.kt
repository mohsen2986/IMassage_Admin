package com.example.imassage_admin.ui.fragment.offer.addOffer

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
import com.example.imassage_admin.databinding.FragmentAddOfferBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.OnCLickHandler
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fra_add_offer.*
import kotlinx.android.synthetic.main.fragment_add_question.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class AddOfferFragment : ScopedFragment() , KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: AddOfferViewModelFactory by instance()

    private lateinit var viewModel: AddOfferViewModel
    private lateinit var navController: NavController
    private lateinit var binding: FragmentAddOfferBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = FragmentAddOfferBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(AddOfferViewModel::class.java)
        uiActions()
    }
    private fun bindUI() = launch {

    }
    private fun uiActions() {
        binding.onClick = object: OnCLickHandler<Nothing>{
            override fun onClickItem(element: Nothing) {}
            override fun onClickView(view: View, element: Nothing) {}
            override fun onClick(view: View) {
                when(view){
                    fra_add_offer_back ->
                        requireActivity().onBackPressed()
                    fra_add_offer_submit ->
                        sendOffers()
                }
            }
        }
    }
    private fun sendOffers() = launch{
        when(val callback = viewModel.createOffer(fra_add_offer_numbers.text.toString() , fra_add_offer_percent.text.toString())){
            is NetworkResponse.Success -> {
                Toast.makeText(context , "با موفقست ساخته شد.", Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            }
        }
    }

}