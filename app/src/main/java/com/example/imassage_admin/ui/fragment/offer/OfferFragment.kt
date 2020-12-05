package com.example.imassage_admin.ui.fragment.offer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import com.example.imassage_admin.R
import com.example.imassage_admin.data.remote.model.NetworkState
import com.example.imassage_admin.databinding.FragmentOfferBinding
import com.example.imassage_admin.ui.adapter.paging.RecyclerAdapter
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.OnCLickHandler
import kotlinx.android.synthetic.main.fragment_offer.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class OfferFragment : ScopedFragment() , KodeinAware{
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: OfferViewModelFactory by instance()

    private lateinit var viewModel: OfferViewModel
    private lateinit var binding: FragmentOfferBinding
    private lateinit var navController: NavController

    // -- FOR DATA
    private lateinit var adapter: RecyclerAdapter<Any>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = FragmentOfferBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(OfferViewModel::class.java)
        configViewModel("")
        configureObservables()
        initAdapter()
        bindUI()
        uiActions()
        fra_offer_recycler.adapter = adapter
    }
    private fun bindUI() = launch {

    }
    private fun uiActions(){
        binding.onClick = object: OnCLickHandler<Nothing>{
            override fun onClickItem(element: Nothing) {}
            override fun onClickView(view: View, element: Nothing) {}
            override fun onClick(view: View) {
                when(view){
                    fra_offer_back ->
                        requireActivity().onBackPressed()

                    fra_offer_add_offer ->
                        navController.navigate(R.id.action_offerFragment_to_addOfferFragment)
                }
            }


        }
    }
    override fun onStart() {
        super.onStart()
        viewModel.refreshAllList()
    }
    private fun initAdapter(){
        adapter = RecyclerAdapter(
                object : RecyclerAdapter.OnClickListener{
                    override fun onRefresh() {

                    }

                    override fun whenListIsUpdated(size: Int, networkState: NetworkState?) {
                    }

                }
        )
        adapter.onClickHandler = object :  OnCLickHandler<Any> {
            override fun onClickItem(element: Any) {
            }

            override fun onClick(view: View) {
            }

            override fun onClickView(view: View, element: Any) {
            }

        }
    }
    private fun configureObservables() {
        viewModel.networkState?.observe(viewLifecycleOwner, Observer { adapter.updateNetworkState(it) })
        viewModel.users.observe(viewLifecycleOwner, Observer { adapter.submitList(it as PagedList<Any>) })
    }
    private fun configViewModel(bundle:String){
        viewModel.fetchQuery(bundle)
    }
    private fun updateUIWhenLoading(size:Int , networkState: NetworkState?){
//        fra_show_items_progress.visibility = if(size == 0 && networkState == NetworkState.RUNNING) View.VISIBLE else View.GONE // todo loading
    }
    private fun updateUIWhenEmptyList(size:Int , networkState: NetworkState?){
        // todo empty list
        /*
        fra_show_items_img_status.visibility = View.GONE
        fra_show_items_status_txt.visibility = View.GONE
        fra_show_items_retry.visibility = View.GONE
        if(size == 0){
            when(networkState){
                NetworkState.SUCCESS ->{
                    Glide.with(this).load(R.drawable.not_found).into(fra_show_items_img_status)
                    fra_show_items_status_txt.text = getString(R.string.items_not_found)
                    fra_show_items_img_status.visibility = View.VISIBLE
                    fra_show_items_status_txt.visibility = View.VISIBLE
                    fra_show_items_retry.visibility = View.GONE
                }
                NetworkState.FAILED ->{
                    Glide.with(this).load(R.drawable.no_connection).into(fra_show_items_img_status)
                    fra_show_items_status_txt.text = getString(R.string.internet_error)
                    fra_show_items_img_status.visibility = View.VISIBLE
                    fra_show_items_status_txt.visibility = View.VISIBLE
                    fra_show_items_retry.visibility = View.VISIBLE
                }
            }
        }*/
    }


}