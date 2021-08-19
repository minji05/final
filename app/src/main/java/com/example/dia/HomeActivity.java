package com.example.dia;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//홈화면
public class HomeActivity extends AppCompatActivity  implements CompoundButton.OnCheckedChangeListener {

    private CheckBox mCheckBox, mCheckBox2, mCheckBox3, mCheckBox4, mCheckBox5;
    String currnet_str, total_str;
    //cnt는 전역 변수로써 사용자의 자가진단 점수
    static int cnt=0;
    int check=cnt;
    //num는 그날 그날 점수
    int num=0;
    //day는 d-day 설정
    static int day=1;
    Button btn_check,btn_update;
    TextView tv, tv_day, tv_date;
    ImageButton btn_left, btn_right;

    final static String dbName="t3.db";
    final static int dbVersion=2;

    DBHelper dbHelper;

    //날짜 가져오기
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy.MM.dd");
    Date mDate;
    long mNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        dbHelper=new DBHelper(this,dbName,null,dbVersion);

        //파이차트 관리
        PieChart mPieChart = (PieChart) findViewById(R.id.tab1_chart_1);

        mPieChart.addPieSlice(new PieModel("일일 달성률",num, Color.parseColor("#44C0AA")));
        mPieChart.addPieSlice(new PieModel("일일 달성률",100-num, Color.parseColor("#C2C2C2")));

        mPieChart.startAnimation();

        PieChart mPieChart2 = (PieChart) findViewById(R.id.tab1_chart_2);

        mPieChart2.addPieSlice(new PieModel("갱년기 점수", cnt, Color.parseColor("#44C0AA")));
        mPieChart2.addPieSlice(new PieModel("갱년기 점수",100-cnt,Color.parseColor("#C2C2C2")));
        mPieChart2.startAnimation();


        //xml랑 연결

        mCheckBox=findViewById(R.id.cb_complete);
        mCheckBox2=findViewById(R.id.cb_complete2);
        mCheckBox3=findViewById(R.id.cb_complete3);
        mCheckBox4=findViewById(R.id.cb_complete4);
        mCheckBox5=findViewById(R.id.cb_complete5);


        btn_check=findViewById(R.id.btn_check);
        btn_update=findViewById(R.id.btn_update);
        tv=findViewById(R.id.tv);
        tv_date=findViewById(R.id.tv_date);
        tv_day=findViewById(R.id.tv_day);
        btn_left=findViewById(R.id.img_left);
        btn_right=findViewById(R.id.img_right);

        //날짜 설정
        tv_date.setText(getTime());
        tv_day.setText("DAY "+day);

        //버튼들이 클릭될때 처리
        mCheckBox.setOnCheckedChangeListener(this);
        mCheckBox2.setOnCheckedChangeListener(this);
        mCheckBox3.setOnCheckedChangeListener(this);
        mCheckBox4.setOnCheckedChangeListener(this);
        mCheckBox5.setOnCheckedChangeListener(this);

        btn_check.setOnClickListener(this::mOnClick);

        btn_right.setOnClickListener(this::mOnClick);
        btn_left.setOnClickListener(this::mOnClick);

        //사용자의 자가 진단 점수에 따라 체크박스의 미션 내용들이 달라짐
        //~40: 낮음, 40~70: 보통, 70~:좋음 --> 기준점
        if(check>300){
            check=check-200;
        }


        if (check < 40) {
            mCheckBox.setText("자가 진단하기");
            mCheckBox2.setText("낮잠 자기");
            mCheckBox3.setText("밥 3끼 먹기");
            mCheckBox4.setText("영양제 챙겨 먹기");
            mCheckBox5.setText("산책 30분 하기");
        } else if (check >= 40) {
            mCheckBox.setText("자가 진단하기");
            mCheckBox2.setText("명상 하기");
            mCheckBox3.setText("제철 과일 먹기");
            mCheckBox4.setText("영양제 챙겨 먹기");
            mCheckBox5.setText("산책 30분 하기");

        } else if (check > 70) {
            mCheckBox.setText("자가 진단하기");
            mCheckBox2.setText("긍정적인 생각하기");
            mCheckBox3.setText("밥 3끼 먹기");
            mCheckBox4.setText("영양제 챙겨 먹기");
            mCheckBox5.setText("산책 1시간 하기");

        } else if (check > 100) {
            mCheckBox.setText("손 지압하기");
            mCheckBox2.setText("독서하기");
            mCheckBox3.setText("제철 과일 먹기");
            mCheckBox4.setText("영양제 챙겨 먹기");
            mCheckBox5.setText("산책 30분 하기");
        } else if (check > 140) {
            mCheckBox.setText("영화 감상하기");
            mCheckBox2.setText("명상 하기");
            mCheckBox3.setText("밥 3끼 먹기");
            mCheckBox4.setText("영양제 챙겨 먹기");
            mCheckBox5.setText("산책 30분 하기");
        } else if (check > 170) {
            mCheckBox.setText("좋아하는 음악듣기");
            mCheckBox2.setText("낮잠 자기");
            mCheckBox3.setText("밥 3끼 먹기");
            mCheckBox4.setText("영양제 챙겨 먹기");
            mCheckBox5.setText("산책 1시간 하기");
        } else if (check > 200) {
            mCheckBox.setText("자가 진단하기");
            mCheckBox2.setText("낮잠 자기");
            mCheckBox3.setText("좋아하는 음식 먹기");
            mCheckBox4.setText("영양제 챙겨 먹기");
            mCheckBox5.setText("산책 30분 하기");

        } else if (check > 240) {
            mCheckBox.setText("자가 진단하기");
            mCheckBox2.setText("취미생활 즐기기");
            mCheckBox3.setText("독서하기");
            mCheckBox4.setText("영양제 챙겨 먹기");
            mCheckBox5.setText("산책 30분 하기");

        } else if (check > 270) {
            mCheckBox.setText("자가 진단하기");
            mCheckBox2.setText("좋아하는 음악 듣기");
            mCheckBox3.setText("명상 하기");
            mCheckBox4.setText("영양제 챙겨 먹기");
            mCheckBox5.setText("산책 1시간 하기");

        }

    }


    //체크박스가 선택될때 처리
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(mCheckBox.isChecked()) num+=20;
        if(mCheckBox2.isChecked()) num+=20;
        if(mCheckBox3.isChecked()) num+=20;
        if(mCheckBox4.isChecked()) num+=20;
        if(mCheckBox5.isChecked()) num+=20;

    }


    public void mOnClick(View v){
        SQLiteDatabase db;
        String sql;

        switch (v.getId()){
            case R.id.btn_check: {
                //총 현황 데이터 베이스에 입력
                String result=Integer.toString(num);
                db = dbHelper.getWritableDatabase();
                sql=String.format("INSERT INTO t3 VALUES("+result+");");
                db.execSQL(sql);
                num=Integer.parseInt(result);
                cnt+=num/10;
                PieChart mPieChart2 = (PieChart) findViewById(R.id.tab1_chart_1);
                mPieChart2.addPieSlice(new PieModel("일일 달성률",100-num, Color.parseColor("#44C0AA")));
                mPieChart2.addPieSlice(new PieModel("일일 달성률",num, Color.parseColor("#C2C2C2")));
                break;
            }


            case R.id.img_left:{
                check=check-100;
                if(day>=1)
                    break;
                else
                    day--;
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH)-1;
                //tv_date.setText(year+"."+month+"."+day);
                tv_date.setText("2021.08.19");
                break;
            }
            case R.id.img_right:{
                check=check+100;
                day++;
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH)+1;
                //tv_date.setText(year+"."+month+"."+day);
                tv_date.setText("2021.08.21");
                tv_day.setText("DAY 2");
                mCheckBox.setText("손 지압하기");
                mCheckBox2.setText("독서하기");
                mCheckBox3.setText("제철 과일 먹기");
                mCheckBox4.setText("영양제 챙겨 먹기");
                mCheckBox5.setText("산책 30분 하기");
                break;
            }



        }dbHelper.close();
    }


    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }


}