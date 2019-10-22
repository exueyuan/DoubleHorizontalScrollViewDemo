package com.duyi.horizontalscrollviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.duyi.horizontalscrollviewdemo.view.DoubleHorizontalAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dhsv_scroll.adapter = MyDoubleHorizontalAdapter()
    }
}
