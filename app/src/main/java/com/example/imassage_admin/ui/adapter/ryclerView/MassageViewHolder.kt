package com.example.imassage_admin.ui.adapter.ryclerView

import androidx.recyclerview.widget.RecyclerView
import com.example.imassage_admin.data.model.Massage
import com.example.imassage_admin.databinding.RowMassagesBinding
import com.example.imassage_admin.ui.utils.OnCLickHandler

class MassageViewHolder(
        private val itemBinding: RowMassagesBinding
): RecyclerView.ViewHolder(itemBinding.root){

    fun <T> bind(element: Massage, onClickHandler: OnCLickHandler<T>?){
        itemBinding.massage = element
        onClickHandler?.let {
            itemBinding.onClick = onClickHandler
        }
    }
}