package com.example.dia.util

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.DialogFragment
import com.example.dia.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class ProgressDialogFragment :DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AppCompatDialog(activity)
        dialog.apply {
            setCancelable(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(R.layout.loading_dialog)
        }
        Glide.with(requireActivity()).load(R.raw.rolling_loader)
                .apply(RequestOptions().override(50,50))
                .into(dialog.findViewById<ImageView>(R.id.load_image_view) as ImageView)
        return dialog
    }
}