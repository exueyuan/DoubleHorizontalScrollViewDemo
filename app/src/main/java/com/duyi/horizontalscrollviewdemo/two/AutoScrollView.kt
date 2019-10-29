package com.duyi.horizontalscrollviewdemo.two

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
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
        /*setOnClickListener {
            stopAnimation()
        }*/
    }

    var allWidth = 0
    var isUseNumAllWidthGreater = false
    var speed = 1

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        //所有元素的长度
        allWidth = 0
        for (i in 0 until childCount) {
            allWidth += getChildAt(i).measuredWidth
        }
        if (childCount > 0) {
            isUseNumAllWidthGreater = width <= (allWidth - getChildAt(childCount - 1).measuredWidth)
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
            val resultShowLeft = if(isUseNumAllWidthGreater){
                getNumIfAllWidthGreater(offsetShowLeft, allWidth, width)
            } else {
                getNumIfScreenWidthGreater(offsetShowLeft, allWidth, width)
            }
            child.layout(resultShowLeft, 0, resultShowLeft + childWidth, childHeight)
            showLeft += childWidth
        }
    }


    //定义动画
    var animator: ValueAnimator? = null

    private fun startAnimation() {
        if (animator == null) {
            animator = ValueAnimator.ofInt(0, 100)
            animator?.duration = 1000
            animator?.repeatCount = ValueAnimator.INFINITE
            animator?.addUpdateListener {
                childOffsetDistence += speed
                doOnLayoutLayout()
            }
            animator?.start()
        }
    }

    private fun stopAnimation() {
        animator?.cancel()
        animator = null
    }

    private fun getNumIfAllWidthGreater(x: Int, allWidth: Int, screenWidth: Int): Int {
        if (allWidth == 0) {
            return 0
        }
        var remainder = x % allWidth
        if (remainder < 0) {
            remainder += allWidth
        }
        return if (remainder <= screenWidth) {
            remainder
        } else {
            remainder - allWidth
        }
    }

    private fun getNumIfScreenWidthGreater(x: Int, allWidth: Int, screenWidth: Int): Int {
        val allLength = allWidth + screenWidth
        var remainder = x % allLength
        if (remainder < 0) {
            remainder += allLength
        }
        return if (remainder <= screenWidth) {
            remainder
        } else {
            remainder - allLength
        }
    }

    var downX:Float = 0f

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                stopAnimation()
                downX = event.x
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = event.x - downX
                downX = event.x
                childOffsetDistence += deltaX.toInt()
                doOnLayoutLayout()
            }
            MotionEvent.ACTION_UP -> {
                startAnimation()
            }
            MotionEvent.ACTION_CANCEL -> {
                startAnimation()
            }
        }
        return true
    }
}