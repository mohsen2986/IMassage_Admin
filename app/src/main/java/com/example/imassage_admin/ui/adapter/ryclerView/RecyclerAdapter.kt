package com.example.imassage_admin.ui.adapter.ryclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imassage_admin.R
import com.example.imassage_admin.data.model.Boarder
import com.example.imassage_admin.data.model.Massage
import com.example.imassage_admin.data.model.Package
import com.example.imassage_admin.databinding.RowMassagesBinding
import com.example.imassage_admin.databinding.RowPackagesBinding
import com.example.imassage_admin.databinding.RowSlidesBinding
import com.example.imassage_admin.ui.utils.OnCLickHandler

class RecyclerAdapter<T>(
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var isGrid = false
    var onClickHandler: OnCLickHandler<T>?= null
    var datas: List<T> = listOf()
    set(value){
        field = value
        notifyDataSetChanged()
    }
    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            R.layout.row_slides -> SliderViewHolder(
                    RowSlidesBinding.inflate(layoutInflater , parent , false)
            )
            R.layout.row_packages -> PackageViewHolder(
                RowPackagesBinding.inflate(layoutInflater , parent , false)
            )
            R.layout.row_massages -> MassageViewHolder(
                    RowMassagesBinding.inflate(layoutInflater , parent , false)
            )
            else -> throw IllegalStateException("the type is invalid!!")
        }
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SliderViewHolder -> holder.bind(datas[position] as Boarder, onClickHandler = onClickHandler)
            is PackageViewHolder -> holder.bind(datas[position]  as Package , onClickHandler = onClickHandler)
            is MassageViewHolder -> holder.bind(datas[position] as Massage , onClickHandler = onClickHandler)
        }
    }

    override fun getItemViewType(position: Int): Int =
            when(datas[0]){
                is Boarder -> R.layout.row_slides
                is Package -> R.layout.row_packages
                is Massage -> R.layout.row_massages

                else -> throw IllegalStateException("the type is invalid!")
            }
}