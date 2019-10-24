package com.duyi.horizontalscrollviewdemo.one.view


import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.View.OnScrollChangeListener
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.annotation.RequiresApi
import com.duyi.horizontalscrollviewdemo.R


@RequiresApi(Build.VERSION_CODES.M)
class DoubleHorizontalScrollView : FrameLayout {
    companion object {
        const val TAG = "DoubleHorizontalScr"
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

    var upScrollWidth = 0
    var downScrollWidth = 0

    lateinit var hsv_up: HorizontalScrollView
    lateinit var hsv_down: HorizontalScrollView
    lateinit var ll_up: LinearLayout
    lateinit var ll_down: LinearLayout

    var adapter: DoubleHorizontalAdapter? = null
        set(value) {
            field = value
            adapter?.doubleHorizontalScrollView = this
            initDataView()
        }

    private fun init() {
        val rootView = View.inflate(
            context,
            R.layout.view_double_horizontal_scroll, this
        )
        hsv_up = rootView.findViewById(R.id.hsv_up)
        hsv_down = rootView.findViewById(R.id.hsv_down)
        ll_up = rootView.findViewById(R.id.ll_up)
        ll_down = rootView.findViewById(R.id.ll_down)

        //刷新数据
        notifyDataSetChanged()

        setUpScrollChangeListnner()
        setDownScrollChangeListnner()
    }


    fun notifyDataSetChanged() {
        initDataView()
        hsv_down.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onGlobalLayout() {
                upScrollWidth = ll_up.width - hsv_up.width
                downScrollWidth = ll_down.width - hsv_down.width
                hsv_down.viewTreeObserver.removeOnGlobalLayoutListener(this)
                hsv_down.fullScroll(ScrollView.FOCUS_RIGHT)
            }
        })
    }

    private fun initDataView() {
        if (adapter != null) {
            ll_up.removeAllViews()
            for (i in 0 until adapter!!.getUpItemCount()) {
                ll_up.addView(adapter!!.getUpItemView(context, i))
            }

            ll_down.removeAllViews()
            for (i in 0 until adapter!!.getDownItemCount()) {
                ll_down.addView(adapter!!.getDownItemView(context, i))
            }
        }
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
            hsv_up.scrollTo(downScrollWidth - scrollX, 0)
            setUpScrollChangeListnner()
        }

    private fun setUpScrollChangeListnner() {
        hsv_up.setOnScrollChangeListener(upScrollChangeListener)
    }

    private fun setDownScrollChangeListnner() {
        hsv_down.setOnScrollChangeListener(downScrollChangeListener)
    }


}