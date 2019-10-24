package com.duyi.horizontalscrollviewdemo.two

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class AutoScrollView : FrameLayout {
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


    private fun init() {

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
//        super.onLayout(changed, left, top, right, bottom)
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val width = child.measuredWidth
            val height = child.measuredHeight
            child.layout(left, top, left + width, top + height)
            child.setOnClickListener {
                child.layout(right-width, bottom-height, right, bottom)
            }
        }
    }
}