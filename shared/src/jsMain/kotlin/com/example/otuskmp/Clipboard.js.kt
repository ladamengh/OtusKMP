package com.example.otuskmp

import kotlinx.browser.window

class JsClipboard : Clipboard {
    override fun copy(text: String) {
        window.navigator.clipboard.writeText(text)
    }
}
