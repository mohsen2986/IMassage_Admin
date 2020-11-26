package com.example.imassage_admin.ui.adapter.paging

import androidx.recyclerview.widget.RecyclerView
import com.example.imassage_admin.data.model.Order
import com.example.imassage_admin.data.model.User
import com.example.imassage_admin.databinding.RowOrderBinding
import com.example.imassage_admin.ui.utils.OnCLickHandler

class OrderViewHolder(
        private val itemBinding: RowOrderBinding
): RecyclerView.ViewHolder(itemBinding.root){

    fun bind(item: Order, onClick: OnCLickHandler<User>?){
        itemBinding.text = item.massage.name
    }
}