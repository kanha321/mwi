package com.kanhaji.ktemplate.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
fun getTheme(): Boolean {
    return if (isSystemTheme) {
        isSystemInDarkTheme()
    } else {
        isDarkTheme
    }
}



fun copyToClipboard(name: String, context: Context) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Video Name", name)
    clipboard.setPrimaryClip(clip)
}