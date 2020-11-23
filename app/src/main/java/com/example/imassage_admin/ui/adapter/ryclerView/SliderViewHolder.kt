package com.example.imassage_admin.ui.adapter.ryclerView

import androidx.recyclerview.widget.RecyclerView
import com.example.imassage_admin.data.model.Boarder
import com.example.imassage_admin.databinding.RowSlidesBinding
import com.example.imassage_admin.ui.utils.OnCLickHandler

class SliderViewHolder(
        private val itemBinding: RowSlidesBinding
): RecyclerView.ViewHolder(itemBinding.root){

    fun <T> bind(item: Boarder, onClickHandler: OnCLickHandler<T>?){
        itemBinding.slide = item
        onClickHandler?.let {
            itemBinding.onClick = onClickHandler
        }
    }
}