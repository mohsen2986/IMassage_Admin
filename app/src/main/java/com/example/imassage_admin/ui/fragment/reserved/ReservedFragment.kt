package com.example.imassage_admin.ui.fragment.reserved

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imassage_admin.R

class ReservedFragment : Fragment() {

    companion object {
        fun newInstance() = ReservedFragment()
    }

    private lateinit var viewModel: ReservedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reserved, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReservedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}