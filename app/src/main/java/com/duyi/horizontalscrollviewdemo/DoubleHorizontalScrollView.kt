package com.duyi.horizontalscrollviewdemo


import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import androidx.annotation.RequiresApi

class DoubleHorizontalScrollView : FrameLayout{
    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    private fun init() {
        val rootView = View.inflate(context, R.layout.view_double_horizontal_scroll, this)
        val hsv_up = rootView.findViewById<HorizontalScrollView>(R.id.hsv_up)
        val hsv_down = rootView.findViewById<HorizontalScrollView>(R.id.hsv_down)
        hsv_down.viewTreeObserver.addOnGlobalLayoutListener(object :ViewTreeObserver.OnGlobalLayoutListener{
            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onGlobalLayout() {
                hsv_down.viewTreeObserver.removeOnGlobalLayoutListener(this)
                hsv_down.fullScroll(ScrollView.FOCUS_RIGHT)
//                hsv_down.scrollTo(ScrollView.FOCUS_RIGHT,0)
            }
        })

    }

}