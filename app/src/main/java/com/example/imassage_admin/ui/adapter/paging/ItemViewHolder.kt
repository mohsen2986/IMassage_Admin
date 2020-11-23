package com.example.imassage_admin.ui.adapter.paging

import androidx.recyclerview.widget.RecyclerView
import com.example.imassage_admin.data.model.User
import com.example.imassage_admin.databinding.RowItemBinding
import com.example.imassage_admin.ui.utils.OnCLickHandler


class ItemViewHolder(
    private val itemViewBinding: RowItemBinding
):RecyclerView.ViewHolder(itemViewBinding.root){
    fun bind(item: User, onClick: OnCLickHandler<User>?){
        itemViewBinding.text = item.name
    }
}