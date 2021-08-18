package com.example.dia

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_on.*

class OnActivity : AppCompatActivity() {

    //  viewpager
    private val adapter by lazy { ViewPagerAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  indicator
        viewPager_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onPageSelected(p0: Int) {
                indicator0_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))
                indicator1_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))
                indicator2_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))
                indicator3_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))

                when(p0){
                    0 -> indicator0_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_purple))
                    1 -> indicator1_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_purple))
                    2 -> indicator2_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_purple))
                    3 -> indicator3_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_purple))
                }
            }
        })

        //  initialize viewpager
        viewPager_main.adapter = MainActivity@ adapter




    }



}



