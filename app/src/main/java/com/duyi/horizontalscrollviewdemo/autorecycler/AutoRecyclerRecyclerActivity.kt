package com.duyi.horizontalscrollviewdemo.autorecycler


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.duyi.horizontalscrollviewdemo.R
import com.duyi.horizontalscrollviewdemo.autorecycler.view.AutoRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_auto_recycler_recycler.*


class AutoRecyclerRecyclerActivity : AppCompatActivity() {

    val list = arrayListOf<BaseData>()

    val adapter = AutoRecyclerViewAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_recycler_recycler)
        for (i in 0..5) {
            list.add(MyData("item:::$i"))
        }

        rv_recycleView.adapter = adapter
        //linearlayout
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rv_recycleView.layoutManager = layoutManager
        rv_recycleView.scrollToPosition(getMiddleNum(list.size))
        rv_recycleView.start()
    }

    private fun getMiddleNum(count: Int):Int {
        if (count == 0) {
            return 0
        }
        val midlle = Int.MAX_VALUE/2
        return midlle - midlle % count
    }
}
