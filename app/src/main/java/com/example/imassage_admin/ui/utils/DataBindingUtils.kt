package com.example.imassage_admin.ui.utils

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class DataBindingUtils() {
companion object {
    @JvmStatic
    @BindingAdapter("android:loadImage")
    fun loadImage(imageView: ImageView, url: String?) {
        if(url != null)
            Glide
                .with(imageView.context)
                .load("http://www.paarandco.ir/IMassage/img/$url")
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("android:setImageFromSD")
    fun setImageFromSD(imageView: ImageView, uri: Uri?) {
        if(uri != null)
            Glide
                    .with(imageView.context)
                    .load(uri)
                    .circleCrop()
                    .into(imageView)
    }
}
}