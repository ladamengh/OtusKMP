package com.example.otuskmp

actual class DecimalFormat actual constructor(pattern: String) {
    // fixme: format with pattern
    actual fun format(value: Double): String {
        return value.asDynamic().toFixed(2).toString()
    }
}