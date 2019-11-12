package com.duyi.horizontalscrollviewdemo.three

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.duyi.horizontalscrollviewdemo.R
import kotlinx.android.synthetic.main.activity_three.*

class ThreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
        dasv_scroll.adapter = MyAutoHorizontalAdapter()
    }
}
