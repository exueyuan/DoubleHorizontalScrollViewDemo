package com.duyi.horizontalscrollviewdemo.three.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.duyi.horizontalscrollviewdemo.R
import com.duyi.horizontalscrollviewdemo.two.AutoScrollView

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

    lateinit var asv_top: AutoScrollView
    lateinit var asv_down: AutoScrollView

    var adapter: DoubleAutoScrollAdapter? = null
        set(value) {
            field = value
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

        //刷新数据
        notifyDataSetChanged()
    }


    fun notifyDataSetChanged() {
        initDataView()
    }

    private fun initDataView() {
        if (adapter != null) {
            asv_top.removeAllViews()
            for (i in 0 until adapter!!.getTopItemCount()) {
                asv_top.addView(adapter!!.getTopItemView(context, i))
            }

            asv_down.removeAllViews()
            for (i in 0 until adapter!!.getDownItemCount()) {
                asv_down.addView(adapter!!.getDownItemView(context, i))
            }
        }
    }


}