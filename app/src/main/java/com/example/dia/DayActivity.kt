package com.example.dia


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup

import androidx.annotation.RequiresApi
import com.example.dia.MainActivity

import kotlinx.android.synthetic.main.activity_main.*


import dagger.hilt.android.AndroidEntryPoint
import java.io.FileOutputStream
import java.time.LocalDate

@AndroidEntryPoint

//하루 기록 화면
class DayActivity : AppCompatActivity() {

    private var mood: String = ""
    private var symptom: String = ""
    private var isExercising: String = ""
    private var isDrinked: String = ""
    private var isSmoking: String = ""
    private var isSleeping: String = ""

    lateinit var saveBtn: Button
    lateinit var backBtn: Button

    var fname: String = ""
    var str: String = ""

    lateinit var mood_radio: RadioGroup
    lateinit var exercising_radio: RadioGroup
    lateinit var drink_radio: RadioGroup
    lateinit var smoking_radio: RadioGroup
    lateinit var sleeping_radio: RadioGroup
    lateinit var mood1: RadioButton
    lateinit var mood2: RadioButton
    lateinit var mood3: RadioButton
    lateinit var mood4: RadioButton
    lateinit var mood5: RadioButton
    lateinit var symptom1: CheckBox
    lateinit var symptom2: CheckBox
    lateinit var symptom3: CheckBox
    lateinit var symptom4: CheckBox
    lateinit var symptom5: CheckBox
    lateinit var ex_zero : RadioButton
    lateinit var ex_30m : RadioButton
    lateinit var ex_1h: RadioButton
    lateinit var ex_1h30m: RadioButton
    lateinit var ex_2h: RadioButton
    lateinit var sleep_5h: RadioButton
    lateinit var sleep_6h: RadioButton
    lateinit var sleep_7h: RadioButton
    lateinit var sleep_8h: RadioButton
    lateinit var sleep_9h: RadioButton


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day)

        //사용할 id 연결
        saveBtn = findViewById(R.id.save_Btn)
        backBtn = findViewById(R.id.back_Btn)
        mood_radio = findViewById(R.id.mood_radio)
        exercising_radio = findViewById(R.id.exercising_radio)
        drink_radio = findViewById(R.id.drink_radio)
        smoking_radio = findViewById(R.id.smoking_radio)
        sleeping_radio = findViewById(R.id.sleeping_radio)
        mood1 = findViewById(R.id.mood1)
        mood2 = findViewById(R.id.mood2)
        mood3 = findViewById(R.id.mood3)
        mood4 = findViewById(R.id.mood4)
        mood5 = findViewById(R.id.mood5)
        symptom1 = findViewById(R.id.symptom1)
        symptom2 = findViewById(R.id.symptom2)
        symptom3 = findViewById(R.id.symptom3)
        symptom4 = findViewById(R.id.symptom4)
        symptom5 = findViewById(R.id.symptom5)
        ex_zero = findViewById(R.id.ex_zero)
        ex_30m = findViewById(R.id.ex_30m)
        ex_1h = findViewById(R.id.ex_1h)
        ex_1h30m = findViewById(R.id.ex_1h30m)
        ex_2h = findViewById(R.id.ex_2h)
        sleep_5h = findViewById(R.id.sleep_5h)
        sleep_6h = findViewById(R.id.sleep_6h)
        sleep_7h = findViewById(R.id.sleep_7h)
        sleep_8h = findViewById(R.id.sleep_8h)
        sleep_9h = findViewById(R.id.sleep_9h)

        //기분 선택
        mood_radio.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.mood1 -> mood = "최고에요"
                R.id.mood2 -> mood = "좋아요"
                R.id.mood3 -> mood = "보통이에요"
                R.id.mood4 -> mood = "안좋아요"
                R.id.mood5 -> mood = "최악이에요"
            }
        }
        //증상 선택
        symptom1.setOnClickListener {
            symptom += "안면홍조 "
        }
        symptom2.setOnClickListener {
            symptom += "근육통 "
        }
        symptom3.setOnClickListener {
            symptom += "불면증 "
        }
        symptom4.setOnClickListener {
            symptom += "식은땀 "
        }
        symptom5.setOnClickListener {
            symptom += "감정기복"
        }
        //운동 시간 선택
        exercising_radio.setOnCheckedChangeListener{radioGroup, checkedId ->
            when(checkedId){
                R.id.ex_zero ->  isExercising = "안함"
                R.id.ex_30m -> isExercising = "30분"
                R.id.ex_1h -> isExercising = "1시간"
                R.id.ex_1h30m -> isExercising = "1시간30분"
                R.id.ex_2h -> isExercising = "2시간 이상"
            }
        }
        //음주 유무 선택
        drink_radio.setOnCheckedChangeListener{radioGroup, i->if(i==R.id.drink_true) isDrinked = "유"
        else isDrinked = "무"}
        //흡연 유무 선택
        smoking_radio.setOnCheckedChangeListener{radioGroup, i->if(i==R.id.smoking_true) isSmoking = "유"
        else isSmoking = "무"}
        //수면 시간 선택
        sleeping_radio.setOnCheckedChangeListener {radioGroup, checkedId ->
            when(checkedId){
                R.id.sleep_5h -> isSleeping = "6시간 미만"
                R.id.sleep_6h -> isSleeping = "6시간"
                R.id.sleep_7h -> isSleeping = "7시간"
                R.id.sleep_8h -> isSleeping = "8시간"
                R.id.sleep_9h -> isSleeping = "8시간 이상"
            }
        }


        //저장 버튼을 누르면 데이터 전달
        saveBtn.setOnClickListener {
            val date = LocalDate.now()
            val sharedPreferences = getSharedPreferences("$date",0)
            val editor = sharedPreferences.edit()
            editor.putString("mood", mood)
            editor.putString("symptom", symptom)
            editor.putString("Exercising", isExercising)
            editor.putString("Drinking", isDrinked)
            editor.putString("Smoking", isSmoking)
            editor.putString("Sleeping", isSleeping)
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)

            intent.getStringExtra("date")
            startActivity(intent)

        }

        //취소 버튼을 누르면 전으로 이동
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    @SuppressLint("WrongConstant")
    fun saveData(personBody: String){

        try {
            var fos: FileOutputStream = openFileOutput(personBody, MODE_PRIVATE)
            var content: String = isExercising + isDrinked + isSmoking + isSleeping
            fos.write(content.toByteArray())
            fos.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}