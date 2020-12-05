package com.example.imassage_admin.ui.adapter.paging

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imassage_admin.R
import com.example.imassage_admin.data.model.Offer
import com.example.imassage_admin.data.model.Order
import com.example.imassage_admin.data.model.User
import com.example.imassage_admin.data.remote.model.NetworkState
import com.example.imassage_admin.databinding.RowItemBinding
import com.example.imassage_admin.databinding.RowLoadingBinding
import com.example.imassage_admin.databinding.RowOfferBinding
import com.example.imassage_admin.databinding.RowOrderBinding
import com.example.imassage_admin.ui.utils.OnCLickHandler

class RecyclerAdapter<T> (
    private val callback: OnClickListener
): PagedListAdapter< T, RecyclerView.ViewHolder>(
    object: DiffUtil.ItemCallback<T>(){
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem
    }
){

//    companion object {
//        private val diffCallBack = object: DiffUtil.ItemCallback<Item>(){
//            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
//                oldItem == newItem
//            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
//                oldItem == newItem
//        }
//    }
    // recycler layers
    private val layers :Map<Any , Int> = mapOf(
        R.layout.row_item to 1 ,
        R.layout.row_loading to 2
    )


    // FOR DATA--
    lateinit var onClickHandler: OnCLickHandler<Any>
    var animation: Int? = null
    private var networkState: NetworkState? = null
    interface OnClickListener{
        fun onRefresh()
        fun whenListIsUpdated(size :Int , networkState: NetworkState?)
    }

    // OVERRIDE ---
    private lateinit var layoutInflater:LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            R.layout.row_item -> ItemViewHolder(
                RowItemBinding.inflate(layoutInflater, parent, false)
            )
            R.layout.row_loading -> LoadingViewHolder(
                RowLoadingBinding.inflate(layoutInflater, parent, false)
            )
            R.layout.row_order -> OrderViewHolder(
                    RowOrderBinding.inflate(layoutInflater , parent , false)
            )
            R.layout.row_offer -> OfferViewHolder(
                    RowOfferBinding.inflate(layoutInflater , parent , false)
            )

            else -> throw IllegalArgumentException("unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            R.layout.row_loading -> (holder as LoadingViewHolder).bind()
            R.layout.row_item -> (holder as ItemViewHolder).bind(getItem(position) as User, null)
            R.layout.row_order -> (holder as OrderViewHolder).bind(getItem(position) as Order , onClick =  onClickHandler)
            R.layout.row_offer -> (holder as OfferViewHolder).bind(getItem(position) as Offer  , onClick =  onClickHandler)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(hasExtraRow() && position == itemCount-1)
            R.layout.row_loading
        else if(getItem(0) is Order)
            R.layout.row_order
        else if(getItem(0) is Offer)
            R.layout.row_offer
        else
            R.layout.row_item
    }


    override fun getItemCount(): Int {
        this.callback.whenListIsUpdated(super.getItemCount() , this.networkState )
        return super.getItemCount()
    }
    // UTILS ---
    private fun hasExtraRow() = networkState != null && networkState != NetworkState.SUCCESS

    // public API
    fun updateNetworkState(networkState: NetworkState){
        val previousNetworkState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = networkState
        val hasExtraRow = hasExtraRow()
        if(hasExtraRow != hadExtraRow){
            if (hadExtraRow)  notifyItemRemoved(super.getItemCount())
            else              notifyItemInserted(super.getItemCount())
        }else if(hadExtraRow && previousNetworkState != networkState){
            notifyItemChanged(itemCount -1)
        }
    }

}