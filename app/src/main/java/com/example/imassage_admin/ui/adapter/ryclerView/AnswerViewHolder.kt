package com.example.imassage_admin.ui.adapter.ryclerView

import androidx.recyclerview.widget.RecyclerView
import com.example.imassage_admin.data.model.Answer
import com.example.imassage_admin.data.model.Question
import com.example.imassage_admin.databinding.RowAnswerBinding
import com.example.imassage_admin.ui.utils.OnCLickHandler

class AnswerViewHolder(
        private val itemBinding: RowAnswerBinding
): RecyclerView.ViewHolder(itemBinding.root){

    fun <T> bind(item: Answer, onClickHandler: OnCLickHandler<T>?) {
        itemBinding.answer = item
    }
}