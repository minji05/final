package com.example.dia


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*

//달력 화면
class CalActivity : AppCompatActivity() {
    lateinit var addBtn: Button
    lateinit var calendarView: CalendarView
    lateinit var diaryTextView: TextView
    lateinit var personmood: TextView
    lateinit var personsymptom: TextView
    lateinit var personexercise: TextView
    lateinit var persondrink: TextView
    lateinit var personsmoking: TextView
    lateinit var personsleep: TextView
    lateinit var imageView: ImageView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cal)

        //액션바 타이틀 없애기
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setSupportActionBar(findViewById(R.id.toolbar))

        //사용할 id 연결
        addBtn = findViewById(R.id.add_Btn)
        calendarView = findViewById(R.id.calendarView)
        diaryTextView = findViewById(R.id.diaryTextView)
        personmood = findViewById(R.id.person_mood)
        personsymptom = findViewById(R.id.person_symptom)
        personexercise = findViewById(R.id.person_exercise)
        persondrink = findViewById(R.id.person_drink)
        personsmoking = findViewById(R.id.person_smoking)
        personsleep = findViewById(R.id.person_sleep)
        imageView = findViewById(R.id.imageView)

        intent.putExtra("todaydate", diaryTextView.text)
        calendarView.callOnClick()

        //달력 날짜 선택되면 날짜 표시, 정보 받아오기
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            diaryTextView.visibility = View.VISIBLE
            diaryTextView.text = String.format("%d - %d - %d", year, month + 1, dayOfMonth)

            val date = diaryTextView.text
            val SharedPreferences = getSharedPreferences("$date", 0)
            personmood.text = SharedPreferences.getString("mood", "-")
            personsymptom.text = SharedPreferences.getString("symptom", "-")
            personexercise.text = SharedPreferences.getString("Exercising", "-")
            persondrink.text = SharedPreferences.getString("Drinking", "-")
            personsmoking.text = SharedPreferences.getString("Smoking", "-")
            personsleep.text = SharedPreferences.getString("Sleeping", "-")

            when (personmood.text) {
                "최고에요" -> {
                    imageView.setImageResource(R.drawable.mood1_2);
                }
                "좋아요" -> {
                    imageView.setImageResource(R.drawable.mood2_2);
                }
                "보통이에요" -> {
                    imageView.setImageResource(R.drawable.mood3_2);
                }
                "안좋아요" -> {
                    imageView.setImageResource(R.drawable.mood4_2);
                }
                "최악이에요" -> {
                    imageView.setImageResource(R.drawable.mood5_2);
                }
                "-" -> {
                    imageView.setImageResource(R.drawable.radiobutton)
                }
            }
        }

        //추가 버튼을 누르면 DayActivity로 이동
        addBtn.setOnClickListener {
            val intent = Intent(this, DayActivity::class.java)

            val date = diaryTextView.text
            intent.putExtra("todaydate", date)
            startActivity(intent)

        }

    }

    //툴바 메뉴 버튼 설정
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        var infoItem: MenuItem? = menu?.findItem(R.id.info)
        var exItem: MenuItem? = menu?.findItem(R.id.ex)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.info -> {
                Intent(this, InfoActivity::class.java)
                startActivity(intent)
            }
            R.id.ex -> {
                Intent(this, SettingProfileActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}