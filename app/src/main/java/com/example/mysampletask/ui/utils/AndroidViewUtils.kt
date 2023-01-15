package com.example.mysampletask.ui.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun AppCompatImageView.displayImage(context: Context, imageUrl: String?){
    Glide.with(context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(false)
        .into(this)
}

fun Context.displayToast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}