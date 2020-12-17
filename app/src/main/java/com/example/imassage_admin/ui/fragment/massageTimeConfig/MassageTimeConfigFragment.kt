package com.example.imassage_admin.ui.fragment.massageTimeConfig

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.R
import com.example.imassage_admin.data.remote.model.TimeConfig
import com.example.imassage_admin.databinding.FragmentMassageTimeConfigBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.OnCLickHandler
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_config.*
import kotlinx.android.synthetic.main.fragment_massage_time_config.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MassageTimeConfigFragment : ScopedFragment() , KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: MassageTimeConfigViewModelFactory by instance()

    private lateinit var viewModel: MassageTimeConfigViewModel
    private lateinit var navController: NavController
    private lateinit var binding: FragmentMassageTimeConfigBinding
    // -- FOR DATA
    private lateinit var packageId: String
    private lateinit var timeConfig: TimeConfig

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = FragmentMassageTimeConfigBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(MassageTimeConfigViewModel::class.java)
        getArgument()
        bindUI()
        sendInitTimeConfig()
        uiActions()
    }
    private fun bindUI() = launch {

    }
    private fun getArgument(){
        packageId = requireArguments().getString("PACKAGE_ID").toString()
    }
    private fun sendInitTimeConfig() = launch{
        when(val callback = viewModel.getPackageTimeConfig(packageId)){
            is NetworkResponse.Success ->{
                timeConfig = callback.body
                binding.config = callback.body
                bind()
            }
        }
    }
    private fun uiActions(){
        binding.onClick = object: OnCLickHandler<Nothing>{
            override fun onClickItem(element: Nothing) {}
            override fun onClick(view: View) {
                when(view){
                    fra_massage_time_config_submit ->
                        updateTimeConfig()
                }
            }
            override fun onClickView(view: View, element: Nothing) {}
        }
    }
    private fun updateTimeConfig() = launch{
        when(val callback = viewModel.updatePackageTimeConfig(
                packageId ,
                if (fra_massage_time_config_active_01.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_01.checkedButtonId == R.id.fra_massage_time_config_man_01) "true" else "false",
                if (fra_massage_time_config_active_02.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_02.checkedButtonId == R.id.fra_massage_time_config_man_02) "true" else "false",
                if (fra_massage_time_config_active_03.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_03.checkedButtonId == R.id.fra_massage_time_config_man_03) "true" else "false",
                if (fra_massage_time_config_active_04.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_04.checkedButtonId == R.id.fra_massage_time_config_man_04) "true" else "false",
                if (fra_massage_time_config_active_05.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_05.checkedButtonId == R.id.fra_massage_time_config_man_05) "true" else "false",
                if (fra_massage_time_config_active_06.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_06.checkedButtonId == R.id.fra_massage_time_config_man_06) "true" else "false",
                if (fra_massage_time_config_active_07.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_07.checkedButtonId == R.id.fra_massage_time_config_man_07) "true" else "false",
                if (fra_massage_time_config_active_08.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_08.checkedButtonId == R.id.fra_massage_time_config_man_08) "true" else "false",
                if (fra_massage_time_config_active_09.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_09.checkedButtonId == R.id.fra_massage_time_config_man_09) "true" else "false",
                if (fra_massage_time_config_active_10.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_10.checkedButtonId == R.id.fra_massage_time_config_man_10) "true" else "false",
                if (fra_massage_time_config_active_11.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_11.checkedButtonId == R.id.fra_massage_time_config_man_11) "true" else "false",
                if (fra_massage_time_config_active_12.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_12.checkedButtonId == R.id.fra_massage_time_config_man_12) "true" else "false",
                if (fra_massage_time_config_active_13.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_13.checkedButtonId == R.id.fra_massage_time_config_man_13) "true" else "false",
                if (fra_massage_time_config_active_14.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_14.checkedButtonId == R.id.fra_massage_time_config_man_14) "true" else "false",
                if (fra_massage_time_config_active_15.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_15.checkedButtonId == R.id.fra_massage_time_config_man_15) "true" else "false",
                if (fra_massage_time_config_active_16.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_16.checkedButtonId == R.id.fra_massage_time_config_man_16) "true" else "false",
                if (fra_massage_time_config_active_17.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_17.checkedButtonId == R.id.fra_massage_time_config_man_17) "true" else "false",
                if (fra_massage_time_config_active_18.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_18.checkedButtonId == R.id.fra_massage_time_config_man_18) "true" else "false",
                if (fra_massage_time_config_active_19.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_19.checkedButtonId == R.id.fra_massage_time_config_man_19) "true" else "false",
                if (fra_massage_time_config_active_20.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_20.checkedButtonId == R.id.fra_massage_time_config_man_20) "true" else "false",
                if (fra_massage_time_config_active_21.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_21.checkedButtonId == R.id.fra_massage_time_config_man_21) "true" else "false",
                if (fra_massage_time_config_active_22.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_22.checkedButtonId == R.id.fra_massage_time_config_man_22) "true" else "false",
                if (fra_massage_time_config_active_23.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_23.checkedButtonId == R.id.fra_massage_time_config_man_23) "true" else "false" ,
                if (fra_massage_time_config_active_00.isChecked) "0" else "1",
                if (fra_massage_time_config_toggle_00.checkedButtonId == R.id.fra_massage_time_config_man_00) "true" else "false"
        )){
            is NetworkResponse.Success ->{
                Toast.makeText(context, "زمان در سیستم ثبت شد." , Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun bind(){
        if(timeConfig.h1Gender == "true")   {fra_massage_time_config_man_01.isChecked = true} else {fra_massage_time_config_woman_01.isChecked = true}
        if(timeConfig.h2Gender == "true")   {fra_massage_time_config_man_02.isChecked = true} else {fra_massage_time_config_woman_02.isChecked = true}
        if(timeConfig.h3Gender == "true")   {fra_massage_time_config_man_03.isChecked = true} else {fra_massage_time_config_woman_03.isChecked = true}
        if(timeConfig.h4Gender == "true")   {fra_massage_time_config_man_04.isChecked = true} else {fra_massage_time_config_woman_04.isChecked = true}
        if(timeConfig.h5Gender == "true")   {fra_massage_time_config_man_05.isChecked = true} else {fra_massage_time_config_woman_05.isChecked = true}
        if(timeConfig.h6Gender == "true")   {fra_massage_time_config_man_06.isChecked = true} else {fra_massage_time_config_woman_06.isChecked = true}
        if(timeConfig.h7Gender == "true")   {fra_massage_time_config_man_07.isChecked = true} else {fra_massage_time_config_woman_07.isChecked = true}
        if(timeConfig.h8Gender == "true")   {fra_massage_time_config_man_08.isChecked = true} else {fra_massage_time_config_woman_08.isChecked = true}
        if(timeConfig.h9Gender == "true")   {fra_massage_time_config_man_09.isChecked = true} else {fra_massage_time_config_woman_09.isChecked = true}
        if(timeConfig.h10Gender == "true")  {fra_massage_time_config_man_10.isChecked = true} else {fra_massage_time_config_woman_10.isChecked = true}
        if(timeConfig.h11Gender == "true")  {fra_massage_time_config_man_11.isChecked = true} else {fra_massage_time_config_woman_11.isChecked = true}
        if(timeConfig.h12Gender == "true")  {fra_massage_time_config_man_12.isChecked = true} else {fra_massage_time_config_woman_12.isChecked = true}
        if(timeConfig.h13Gender == "true")  {fra_massage_time_config_man_13.isChecked = true} else {fra_massage_time_config_woman_13.isChecked = true}
        if(timeConfig.h14Gender == "true")  {fra_massage_time_config_man_14.isChecked = true} else {fra_massage_time_config_woman_14.isChecked = true}
        if(timeConfig.h15Gender == "true")  {fra_massage_time_config_man_15.isChecked = true} else {fra_massage_time_config_woman_15.isChecked = true}
        if(timeConfig.h16Gender == "true")  {fra_massage_time_config_man_16.isChecked = true} else {fra_massage_time_config_woman_16.isChecked = true}
        if(timeConfig.h17Gender == "true")  {fra_massage_time_config_man_17.isChecked = true} else {fra_massage_time_config_woman_17.isChecked = true}
        if(timeConfig.h18Gender == "true")  {fra_massage_time_config_man_18.isChecked = true} else {fra_massage_time_config_woman_18.isChecked = true}
        if(timeConfig.h19Gender == "true")  {fra_massage_time_config_man_19.isChecked = true} else {fra_massage_time_config_woman_19.isChecked = true}
        if(timeConfig.h20Gender == "true")  {fra_massage_time_config_man_20.isChecked = true} else {fra_massage_time_config_woman_20.isChecked = true}
        if(timeConfig.h21Gender == "true")  {fra_massage_time_config_man_21.isChecked = true} else {fra_massage_time_config_woman_21.isChecked = true}
        if(timeConfig.h22Gender == "true")  {fra_massage_time_config_man_22.isChecked = true} else {fra_massage_time_config_woman_22.isChecked = true}
        if(timeConfig.h23Gender == "true")  {fra_massage_time_config_man_23.isChecked = true} else {fra_massage_time_config_woman_23.isChecked = true}
        if(timeConfig.h24Gender == "true")  {fra_massage_time_config_man_00.isChecked = true} else {fra_massage_time_config_woman_00.isChecked = true}
    }

}