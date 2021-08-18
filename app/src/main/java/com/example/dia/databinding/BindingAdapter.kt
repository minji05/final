package com.example.dia.databinding

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.dia.R


object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setColorFilter")
    fun setColorFilter(imageView: AppCompatImageView, like:Int){
        if(like==1)
            imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.teal_700), android.graphics.PorterDuff.Mode.SRC_IN)
        else
            imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.teal_700), android.graphics.PorterDuff.Mode.SRC_IN)
    }

    @JvmStatic
    @BindingAdapter("setOnEditorActionListener")
    fun setOnEditorActionListener(editText: EditText, listener:TextView.OnEditorActionListener){
        editText.setOnEditorActionListener(listener)
    }
}