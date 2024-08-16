package com.example.ghkt_leak_memmory

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var leakyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        leakyTextView = findViewById(R.id.leakyTextView)

        // Tạo một CPU leak bằng cách sử dụng Handler liên tục gửi thông điệp
        handler = Handler(Looper.getMainLooper())
        startCpuLeak()

        // Tạo một memory leak bằng cách giữ tham chiếu đến Activity context trong một class tĩnh
        LeakySingleton.setLeakyTextView(leakyTextView)
    }

    private fun startCpuLeak() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                // Công việc giả sử chiếm CPU mà không bao giờ dừng
                leakyTextView.text = (leakyTextView.text.toString().toInt() + 1).toString()
                handler.postDelayed(this, 1000)
            }
        }, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Không hủy bỏ Handler, dẫn đến CPU leak
        // handler.removeCallbacksAndMessages(null) // Sửa lỗi này bằng cách hủy bỏ các công việc
    }
}