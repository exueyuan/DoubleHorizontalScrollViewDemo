package com.duyi.horizontalscrollviewdemo.two

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.duyi.horizontalscrollviewdemo.R
import kotlinx.android.synthetic.main.activity_two.*

class TwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        tv_222.setOnClickListener {
            Toast.makeText(this,"222",Toast.LENGTH_SHORT).show()
        }
        fl_111.setOnClickListener {
            Toast.makeText(this,"111",Toast.LENGTH_SHORT).show()
        }
    }
}
