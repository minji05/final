package com.example.dia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {

    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        //사용할 id 연결
        button = findViewById(R.id.button)

        //취소 버튼을 누르면 캘린더 화면으로 돌아감
        button.setOnClickListener {
            val intent = Intent(this, CalActivity::class.java)
            startActivity(intent)
        }
    }
}