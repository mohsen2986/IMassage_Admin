package com.example.imassage_admin.ui.adapter.ryclerView

import androidx.recyclerview.widget.RecyclerView
import com.example.imassage_admin.data.model.User
import com.example.imassage_admin.databinding.RowUserBinding
import com.example.imassage_admin.ui.utils.OnCLickHandler

class UserViewHolder(
    private val itemBinding: RowUserBinding
): RecyclerView.ViewHolder(itemBinding.root){
    
    fun <T> bind(item: User , onClickHandler: OnCLickHandler<T>?){
        itemBinding.user = item
        onClickHandler?.let {
            itemBinding.onClick = onClickHandler
        }
    }
    
}