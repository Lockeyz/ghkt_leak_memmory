package com.example.ghkt_leak_memmory

import android.annotation.SuppressLint
import android.widget.TextView

// Singleton giữ tham chiếu đến TextView gây ra memory leak
object LeakySingleton {
    @SuppressLint("StaticFieldLeak")
    private var leakyTextView: TextView? = null

    fun setLeakyTextView(textView: TextView) {
        leakyTextView = textView
    }
}