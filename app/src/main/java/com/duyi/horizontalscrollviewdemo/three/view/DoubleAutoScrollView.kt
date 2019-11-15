package com.duyi.horizontalscrollviewdemo.three.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.duyi.horizontalscrollviewdemo.R
import com.duyi.horizontalscrollviewdemo.three.FLStack

class DoubleAutoScrollView : FrameLayout {
    companion object {
        const val TAG = "DoubleAutoScrollView"
    }

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

    private val topStack = FLStack<View>()
    private val bottomStack = FLStack<View>()

    lateinit var asv_top: AutoScrollView
    lateinit var asv_down: AutoScrollView

    var adapter: DoubleAutoScrollAdapter? = null
        set(value) {
            field = value
            adapter?.doubleAutoScrollView = this
            initDataView()
        }

    private fun init() {
        val rootView = View.inflate(
            context,
            R.layout.view_double_auto_scroll_view, this
        )
        asv_top = rootView.findViewById(R.id.asv_top)
        asv_down = rootView.findViewById(R.id.asv_down)
        asv_top.direction = AutoScrollView.Direction.LEFT
        asv_down.direction = AutoScrollView.Direction.RIGHT
        asv_top.speed = -1
        asv_down.speed = 1

        asv_top.stopCallBack = {
            asv_down.isStop = it
        }
        asv_down.stopCallBack = {
            asv_top.isStop = it
        }

        asv_top.scrollCallBack = { oldX, newX, changeX ->
            Log.i(TAG,"top:oldX:${oldX}, newX:${newX}, changeX:${changeX}")
            if (asv_down.isStop) {
                asv_down.setDistence(newX)
            }
        }

        asv_down.scrollCallBack = { oldX, newX, changeX ->
            Log.i(TAG,"bottom:oldX:${oldX}, newX:${newX}, changeX:${changeX}")
            if (asv_top.isStop) {
                asv_top.setDistence(newX)
            }
        }

        //刷新数据
        notifyDataSetChanged()
    }


    fun notifyDataSetChanged() {
        initDataView()
    }

    private fun initDataView() {
        if (adapter != null) {
            removeAllChildView(asv_top, topStack)
            for (i in 0 until adapter!!.getTopItemCount()) {
                val cacheView = topStack.pop()
                asv_top.addView(adapter!!.getTopItemView(context, i, cacheView))
            }

            removeAllChildView(asv_down, bottomStack)
            for (i in 0 until adapter!!.getDownItemCount()) {
                val cacheView = bottomStack.pop()
                asv_down.addView(adapter!!.getDownItemView(context, i, cacheView))
            }
        }

        if (asv_down.childCount > 0) {
            asv_down.visibility = View.VISIBLE
        } else {
            asv_down.visibility = View.GONE
        }
    }

    private fun removeAllChildView(vg: ViewGroup, topStack: FLStack<View>) {
        for (i in 0 until vg.childCount) {
            val childView = vg.getChildAt(i)
            vg.removeView(childView)
            topStack.push(childView)
        }
    }


}