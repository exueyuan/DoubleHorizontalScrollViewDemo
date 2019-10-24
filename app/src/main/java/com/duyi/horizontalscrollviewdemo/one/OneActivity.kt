package com.duyi.horizontalscrollviewdemo.one

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.duyi.horizontalscrollviewdemo.R
import kotlinx.android.synthetic.main.activity_one.*

class OneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        dhsv_scroll.adapter = MyDoubleHorizontalAdapter()
    }
}
