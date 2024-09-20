package com.kanhaji.ktemplate.util

import android.content.Context
import android.widget.Toast

object MyToast {
    private var toast: Toast? = null
    fun show(
        context: Context,
        text: String,
        duration: Int = Toast.LENGTH_SHORT
    ) {
        toast?.cancel()
        toast = Toast.makeText(context, text, duration)
        toast?.show()
    }
}