package com.duyi.horizontalscrollviewdemo


import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.View.OnScrollChangeListener
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.M)
class DoubleHorizontalScrollView : FrameLayout{
    companion object{
        const val TAG = "DoubleHorizontalScr"
    }
    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    var upScrollWidth = 0
    var downScrollWidth = 0

    lateinit var hsv_up:HorizontalScrollView
    lateinit var hsv_down:HorizontalScrollView

    private fun init() {
        val rootView = View.inflate(context, R.layout.view_double_horizontal_scroll, this)
        hsv_up = rootView.findViewById<HorizontalScrollView>(R.id.hsv_up)
        hsv_down = rootView.findViewById<HorizontalScrollView>(R.id.hsv_down)
        val ll_up = rootView.findViewById<View>(R.id.ll_up)
        val ll_down = rootView.findViewById<View>(R.id.ll_down)
        hsv_down.viewTreeObserver.addOnGlobalLayoutListener(object :ViewTreeObserver.OnGlobalLayoutListener{
            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onGlobalLayout() {
                upScrollWidth = ll_up.width - hsv_up.width
                downScrollWidth = ll_down.width - hsv_down.width
                hsv_down.viewTreeObserver.removeOnGlobalLayoutListener(this)
                hsv_down.fullScroll(ScrollView.FOCUS_RIGHT)
            }
        })

        setUpScrollChangeListnner()
        setDownScrollChangeListnner()
    }

    private val upScrollChangeListener =
        OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            hsv_down.setOnScrollChangeListener(null)
            hsv_down.scrollTo(downScrollWidth - scrollX, 0)
            setDownScrollChangeListnner()
        }

    private val downScrollChangeListener =
        OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            hsv_up.setOnScrollChangeListener(null)
            hsv_up.scrollTo(downScrollWidth - scrollX , 0)
            setUpScrollChangeListnner()
        }

    fun setUpScrollChangeListnner() {
        hsv_up.setOnScrollChangeListener(upScrollChangeListener)
    }

    fun setDownScrollChangeListnner() {
        hsv_down.setOnScrollChangeListener(downScrollChangeListener)
    }


}