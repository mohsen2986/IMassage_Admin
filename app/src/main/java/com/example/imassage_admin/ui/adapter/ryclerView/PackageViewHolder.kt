package com.example.imassage_admin.ui.adapter.ryclerView

import androidx.recyclerview.widget.RecyclerView
import com.example.imassage_admin.data.model.Package
import com.example.imassage_admin.databinding.RowPackagesBinding
import com.example.imassage_admin.ui.utils.OnCLickHandler

class PackageViewHolder(
    private val itemBinding: RowPackagesBinding
): RecyclerView.ViewHolder(itemBinding.root){
    fun <T> bind( item: Package , onClickHandler: OnCLickHandler<T>?){
        itemBinding.packages = item
        onClickHandler?.let{
            itemBinding.onClick = onClickHandler
        }
    }
}