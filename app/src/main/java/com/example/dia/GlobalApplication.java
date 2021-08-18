package com.example.dia;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

//카카오톡 api 연결
public class GlobalApplication extends Application {
    private static GlobalApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // 네이티브 앱 키로 초기화
        KakaoSdk.init(this, "8a670e86ec1997daaa82ca5c8f08955c" );
    }
}
