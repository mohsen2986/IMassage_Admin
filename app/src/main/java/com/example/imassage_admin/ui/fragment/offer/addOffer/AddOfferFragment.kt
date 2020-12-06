package com.example.imassage_admin.ui.fragment.offer.addOffer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.R
import com.example.imassage_admin.data.model.Massage
import com.example.imassage_admin.databinding.FragmentAddOfferBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.OnCLickHandler
import com.haroldadmin.cnradapter.NetworkResponse
import com.wdullaer.materialdatetimepicker.JalaliCalendar
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_add_offer.*
import kotlinx.android.synthetic.main.fragment_add_question.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.util.*

class AddOfferFragment : ScopedFragment() , KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: AddOfferViewModelFactory by instance()

    private lateinit var viewModel: AddOfferViewModel
    private lateinit var navController: NavController
    private lateinit var binding: FragmentAddOfferBinding

    // -FOR DATA
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var massages: List<Massage>
    private lateinit var selectedMassage: String
    private var calendarType: DatePickerDialog.Type = DatePickerDialog.Type.JALALI

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
        initAdapter()
        bindAdapter()
        bindUI()
        uiActions()
    }
    private fun bindUI() = launch {
        when(val callback = viewModel.massages()){
            is NetworkResponse.Success ->{
                massages = callback.body.datas
                adapter.addAll(massages.map { it.name })
            }
        }
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
                    fra_add_offer_start_date -> {
                        val now: JalaliCalendar = JalaliCalendar.getInstance()
                        val dp = DatePickerDialog.newInstance(
                                calendarType,
                                { view, year, monthOfYear, dayOfMonth ->
                                },
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH)
                        )
                        dp.onDateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                            "$year-$monthOfYear-$dayOfMonth".let {
                                fra_add_offer_start_date.setText(it)
                                Toast.makeText(requireContext() , "$id" , Toast.LENGTH_SHORT).show()
                            }
                        }
                        dp.show(requireActivity().supportFragmentManager, "DatePickerDialog")
                    }
                    fra_add_offer_end_date -> {
                        val now: JalaliCalendar = JalaliCalendar.getInstance()
                        val dp = DatePickerDialog.newInstance(
                                calendarType,
                                { view, year, monthOfYear, dayOfMonth ->
                                },
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH)
                        )
                        dp.onDateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                            "$year-$monthOfYear-$dayOfMonth".let {
                                fra_add_offer_end_date.setText(it)
                                Toast.makeText(requireContext() , "$id" , Toast.LENGTH_SHORT).show()
                            }
                        }
                        dp.show(requireActivity().supportFragmentManager, "DatePickerDialog")
                    }
                }
            }
        }
        fra_add_offer_select_massage.onItemClickListener = AdapterView.OnItemClickListener { parent, p1, position, p3 ->
            Toast.makeText(context ,"${parent?.getItemAtPosition(position)}" , Toast.LENGTH_LONG).show()
            selectedMassage = getMassageId(parent?.getItemAtPosition(position).toString())
        }
    }
    private fun sendOffers() = launch{
//        if(fra_add_offer_percent.text?.isNotEmpty()!! && fra_add_offer_numbers.text?.isNotEmpty()!! && fra_add_offer_end_date.text?.isNotEmpty()!! && fra_add_offer_start_date.text?.isNotEmpty()!!) {
            when (val callback = viewModel.createOffer(fra_add_offer_numbers.text.toString(), fra_add_offer_percent.text.toString() ,
                    "2" , fra_add_offer_start_date.text.toString() , fra_add_offer_end_date.text.toString())
                ) {
                is NetworkResponse.Success -> {
                    Toast.makeText(context, "با موفقست ساخته شد.", Toast.LENGTH_SHORT).show()
                    requireActivity().onBackPressed()
                }
            }
//        }else{
//            Toast.makeText(requireContext() , "همه مقادیر را واد کنید." , Toast.LENGTH_SHORT).show()
//        }
    }
    private fun initAdapter(){
        adapter = ArrayAdapter( requireContext() , R.layout.dropdown_select_massage)
    }
    private fun bindAdapter(){
        fra_add_offer_select_massage.setAdapter(adapter)
    }
    private fun getMassageId(massageName: String) =
            massages.find { it.name == massageName }!!.id

}