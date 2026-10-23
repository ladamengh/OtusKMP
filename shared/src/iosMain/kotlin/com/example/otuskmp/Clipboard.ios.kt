package com.example.otuskmp

import platform.UIKit.UIPasteboard

class IosClipboard : Clipboard {
    override fun copy(text: String) {
        UIPasteboard.generalPasteboard.string = text
    }
}
