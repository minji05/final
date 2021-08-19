package com.example.dia

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SettingProfileActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var editbtn: Button
    lateinit var logoutbtn: Button
    lateinit var profile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settingprofile)

        //사용할 id 연결
        button = findViewById(R.id.button)
        editbtn = findViewById(R.id.editbtn)
        logoutbtn = findViewById(R.id.logoutBtn)
        profile = findViewById(R.id.profile)

        //취소 버튼을 누르면 캘린더 화면으로 돌아감
        button.setOnClickListener {
            val intent = Intent(this, CalActivity::class.java)
            startActivity(intent)
        }

        //수정 버튼을 누르면 프로필 이미지 수정
        editbtn.setOnClickListener {

        }

        //로그아웃 버튼을 누르면 로그아웃
        logoutbtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut();

        }
    }
}