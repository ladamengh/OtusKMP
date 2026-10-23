package com.example.otuskmp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class AndroidClipboard(
    private val context: Context,
) : Clipboard {
    override fun copy(text: String) {
        val clipboard = context.applicationContext
            .getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("Stopwatch result", text))
    }
}
