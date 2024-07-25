package com.example.imagegalleryapp.presentation.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.example.imagegalleryapp.R
import com.example.imagegalleryapp.databinding.PopUpViewBinding
import tpc.com.uy.t2parking.util.models.CustomDialogData

object Message {

    fun showPopUpDialog(ctx: Context, dialogData: CustomDialogData, layoutInflater: LayoutInflater) {

        val dialogBinding = PopUpViewBinding.inflate(layoutInflater).apply {
            messageTxt.text = dialogData.message

        }
        val dialog = Dialog(ctx).apply {
            setCancelable(true)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(dialogBinding.root)
        }
        dialogBinding.confirmBtn.setOnClickListener {
            dialogData.firstOnClick?.invoke()
            dialog.dismiss()
        }

        dialogBinding.closeOrAcceptBtn.setOnClickListener {
            dialog.dismiss()
        }
        showDialog(dialog)
    }

    fun showDialog(dialog:Dialog){
        dialog.show()
        dialog.window?.let {
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.attributes.windowAnimations = R.style.DialogAnimation
            it.setGravity(Gravity.BOTTOM)
        }
    }
}