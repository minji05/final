package com.example.dia;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//설정 창, SettingFragment와 연결해서 쓰임
public class SettingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_pref, new SettingFragment())
                .commit();



    }
}
