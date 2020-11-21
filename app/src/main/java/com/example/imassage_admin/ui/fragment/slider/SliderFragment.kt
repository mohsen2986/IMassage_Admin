package com.example.imassage_admin.ui.fragment.slider

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imassage_admin.R

class SliderFragment : Fragment() {

    companion object {
        fun newInstance() = SliderFragment()
    }

    private lateinit var viewModel: SliderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_slider, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SliderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}