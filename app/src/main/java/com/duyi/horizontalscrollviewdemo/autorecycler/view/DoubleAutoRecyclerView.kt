package com.duyi.horizontalscrollviewdemo.autorecycler.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duyi.horizontalscrollviewdemo.R

class DoubleAutoRecyclerView<T> : FrameLayout {
    companion object {
        const val TAG = "DoubleAutoRecyclerView"
    }

    val topList = arrayListOf<T>()
    val bottomList = arrayListOf<T>()

    lateinit var doubleAutoRecyclerAdapter: DoubleAutoRecyclerAdapter<T>

    val topAdapter = AutoRecyclerViewAdapter(
        topList,
        onBindViewHolderCallback = { holder: RecyclerView.ViewHolder, position: Int, data: T ->
            doubleAutoRecyclerAdapter.onTopBindViewHolder(holder, position, data)
        })
    val bottomAdapter = AutoRecyclerViewAdapter(
        bottomList,
        onBindViewHolderCallback = { holder: RecyclerView.ViewHolder, position: Int, data: T ->
            doubleAutoRecyclerAdapter.onTopBindViewHolder(holder, position, data)
        })

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    lateinit var arv_top: AutoRecyclerView
    lateinit var arv_bottom: AutoRecyclerView


    fun setDoubleAutoRecyclerAdapterAdapter(doubleAutoRecyclerAdapter: DoubleAutoRecyclerAdapter<T>) {
        this.doubleAutoRecyclerAdapter = doubleAutoRecyclerAdapter
        doubleAutoRecyclerAdapter.doubleAutoRecyclerView = this
        topAdapter.doubleAutoRecyclerAdapter = doubleAutoRecyclerAdapter
        bottomAdapter.doubleAutoRecyclerAdapter = doubleAutoRecyclerAdapter

        arv_top.adapter = topAdapter
        arv_bottom.adapter = bottomAdapter

        notifyDataSetChanged()
    }

    private fun init() {
        val rootView = View.inflate(
            context,
            R.layout.view_double_auto_recycler_view, this
        )
        //获取控件
        arv_top = rootView.findViewById(R.id.arv_top)
        arv_bottom = rootView.findViewById(R.id.arv_bottom)


        //设置上面的recyclerview
        val topLayoutManager = LinearLayoutManager(context)
        topLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        arv_top.layoutManager = topLayoutManager


        //设置下面的recyclerview
        val bottomLayoutManager = LinearLayoutManager(context)
        bottomLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        arv_bottom.layoutManager = bottomLayoutManager
    }


    fun notifyDataSetChanged() {
        //上面的
        topList.clear()
        topList.addAll(doubleAutoRecyclerAdapter.getTopList())
        topAdapter.notifyDataSetChanged()
        arv_top.speed = 2
        arv_top.scrollToPosition(getMiddleNum(topList.size))
        arv_top.start()

        //下面的
        bottomList.clear()
        bottomList.addAll(doubleAutoRecyclerAdapter.getBottomList())
        bottomAdapter.notifyDataSetChanged()
        arv_bottom.speed = -2
        arv_bottom.scrollToPosition(getMiddleNum(topList.size))
        arv_bottom.start()
    }

    private fun getMiddleNum(count: Int): Int {
        if (count == 0) {
            return 0
        }
        val midlle = Int.MAX_VALUE / 2
        return midlle - midlle % count
    }


}