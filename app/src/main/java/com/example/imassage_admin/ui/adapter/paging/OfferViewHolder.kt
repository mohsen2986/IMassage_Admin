package com.example.imassage_admin.ui.adapter.paging

import androidx.recyclerview.widget.RecyclerView
import com.example.imassage_admin.data.model.Offer
import com.example.imassage_admin.data.model.User
import com.example.imassage_admin.databinding.RowOfferBinding
import com.example.imassage_admin.ui.utils.OnCLickHandler

class OfferViewHolder(
        private val itemViewBinding: RowOfferBinding
): RecyclerView.ViewHolder(itemViewBinding.root){
    fun bind(item: Offer, onClick: OnCLickHandler<Any>?){
        itemViewBinding.offer= item
    }
}