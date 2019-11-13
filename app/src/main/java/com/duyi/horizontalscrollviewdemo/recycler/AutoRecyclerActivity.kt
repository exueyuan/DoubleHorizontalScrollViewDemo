package com.duyi.horizontalscrollviewdemo.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.duyi.horizontalscrollviewdemo.recycler.view.AutoPollAdapter
import com.duyi.horizontalscrollviewdemo.recycler.view.AutoPollRecyclerView



class AutoRecyclerActivity : AppCompatActivity() {

    private var mRecyclerView: AutoPollRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.duyi.horizontalscrollviewdemo.R.layout.activity_auto_recycler)
        initView()
    }

    private fun initView() {
        mRecyclerView = findViewById<AutoPollRecyclerView>(com.duyi.horizontalscrollviewdemo.R.id.rv_recycleView)
        val list = arrayListOf<String>()
        var i = 0
        while (i < 5) {
            list.add(" Item: " + ++i)
        }
        val adapter = AutoPollAdapter(list)
        mRecyclerView!!.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        mRecyclerView!!.adapter = adapter
        mRecyclerView!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (null != mRecyclerView) {
            mRecyclerView!!.stop()
        }
    }
}
