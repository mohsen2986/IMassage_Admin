package com.example.imassage_admin.ui.utils

import android.view.View

interface OnCLickHandler<T>{
    fun onClickItem(element: T)
    fun onClick(view: View)
    fun onClickView(view: View , element: T)
}