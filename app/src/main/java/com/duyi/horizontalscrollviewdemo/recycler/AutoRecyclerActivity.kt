package com.duyi.horizontalscrollviewdemo.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.duyi.horizontalscrollviewdemo.R
import com.duyi.horizontalscrollviewdemo.recycler.view.AutoPollAdapter
import com.duyi.horizontalscrollviewdemo.recycler.view.AutoPollRecyclerView
import kotlinx.android.synthetic.main.activity_auto_recycler.*


class AutoRecyclerActivity : AppCompatActivity() {

    private var mRecyclerView: AutoPollRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_recycler)
        initView()
    }

    private fun initView() {
        val list = arrayListOf<String>()
        var i = 0
        while (i < 5) {
            list.add(" Item: " + ++i)
        }
        val adapter = AutoPollAdapter(list)
        rv_recycleView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        rv_recycleView.adapter = adapter
        rv_recycleView.scrollToPosition(getMiddleNum(list.size))
        rv_recycleView.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (null != mRecyclerView) {
            mRecyclerView!!.stop()
        }
    }

    fun getMiddleNum(count: Int):Int {
        if (count == 0) {
            return 0
        }
        val midlle = Int.MAX_VALUE/2
        return midlle - midlle % count
    }
}
