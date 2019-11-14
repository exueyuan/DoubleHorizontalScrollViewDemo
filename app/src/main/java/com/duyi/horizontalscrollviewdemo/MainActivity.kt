package com.duyi.horizontalscrollviewdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.duyi.horizontalscrollviewdemo.autorecycler.AutoRecyclerRecyclerActivity
import com.duyi.horizontalscrollviewdemo.cache.HasCacheActivity
import com.duyi.horizontalscrollviewdemo.one.OneActivity
import com.duyi.horizontalscrollviewdemo.recycler.AutoRecyclerActivity
import com.duyi.horizontalscrollviewdemo.three.ThreeActivity
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

        bt_three.setOnClickListener {
            startActivity(Intent(this, ThreeActivity::class.java))
        }

        bt_cache.setOnClickListener {
            startActivity(Intent(this, HasCacheActivity::class.java))
        }

        bt_recycler.setOnClickListener {
            startActivity(Intent(this, AutoRecyclerActivity::class.java))
        }

        bt_auto_recycler.setOnClickListener {
            startActivity(Intent(this, AutoRecyclerRecyclerActivity::class.java))
        }
    }
}
