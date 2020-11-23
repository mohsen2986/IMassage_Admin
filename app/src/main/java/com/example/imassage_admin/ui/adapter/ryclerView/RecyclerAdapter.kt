package com.example.imassage_admin.ui.adapter.ryclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imassage_admin.R
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
            else -> throw IllegalStateException("the type is invalid!!")
        }
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){

        }
    }

    override fun getItemViewType(position: Int): Int =
            when(datas[0]){
                else -> throw IllegalStateException("the type is invalid!")
            }
}