package com.duyi.horizontalscrollviewdemo.two

import android.animation.ValueAnimator
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
        setOnClickListener {
            stopAnimation()
        }
    }

    var allWidth = 0

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        //所有元素的长度
        allWidth = 0
        for (i in 0 until childCount) {
            allWidth += getChildAt(i).measuredWidth
        }
        doOnLayoutLayout()

        startAnimation()
    }

    var childOffsetDistence = 0
    private fun doOnLayoutLayout() {
        var showLeft = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight
            val offsetShowLeft = childOffsetDistence + showLeft
            val resultShowLeft = getNum(offsetShowLeft, allWidth, width)
            child.layout(resultShowLeft, 0, resultShowLeft + childWidth, childHeight)
            showLeft += childWidth
        }
    }


    //定义动画
    var animator: ValueAnimator? = null

    private fun startAnimation() {
        animator = ValueAnimator.ofInt(0, 100)
        animator?.duration = 1000
        animator?.repeatCount = ValueAnimator.INFINITE
        animator?.addUpdateListener {
            childOffsetDistence -= 5
            doOnLayoutLayout()
        }
        animator?.start()
    }

    private fun stopAnimation() {
        animator?.cancel()
    }

    private fun getNum(x: Int, allWidth: Int, screenWidth: Int): Int {
        if (allWidth == 0) {
            return 0
        }
        var a = x % allWidth
        if (a < 0) {
            a += allWidth
        }
        return if (a <= screenWidth) {
            a
        } else {
            a - allWidth
        }
    }
}