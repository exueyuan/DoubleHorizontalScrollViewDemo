package com.duyi.horizontalscrollviewdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.duyi.horizontalscrollviewdemo.one.OneActivity
import com.duyi.horizontalscrollviewdemo.two.TwoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_one.setOnClickListener {
            startActivity(Intent(this, OneActivity::class.java))
        }

        bt_two.setOnClickListener {
            startActivity(Intent(this, TwoActivity::class.java))
        }
    }
}
