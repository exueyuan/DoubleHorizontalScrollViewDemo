package com.duyi.horizontalscrollviewdemo.autorecycler


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.duyi.horizontalscrollviewdemo.R


class AutoRecyclerRecyclerActivity : AppCompatActivity() {

    val toplist = arrayListOf<BaseData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_recycler_recycler)
        for (i in 0..10) {
            toplist.add(MyData("item:::$i"))
        }
    }
}
