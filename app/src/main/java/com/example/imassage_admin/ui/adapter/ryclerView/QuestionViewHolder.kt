package com.example.imassage_admin.ui.adapter.ryclerView

import androidx.recyclerview.widget.RecyclerView
import com.example.imassage_admin.data.model.Package
import com.example.imassage_admin.data.model.Question
import com.example.imassage_admin.databinding.RowQuestionBinding
import com.example.imassage_admin.ui.utils.OnCLickHandler

class QuestionViewHolder(
        private val itemBinding: RowQuestionBinding
): RecyclerView.ViewHolder(itemBinding.root){

    fun <T> bind(item: Question, onClickHandler: OnCLickHandler<T>?){
        itemBinding.question = item
        onClickHandler?.let{
            itemBinding.onClick = onClickHandler
        }
    }
}