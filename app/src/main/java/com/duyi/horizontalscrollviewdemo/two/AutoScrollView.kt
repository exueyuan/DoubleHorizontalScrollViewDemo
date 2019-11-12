package com.duyi.horizontalscrollviewdemo.two

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout

class AutoScrollView : FrameLayout {
    companion object {
        const val TAG = "AutoScrollView"
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    private var allWidth = 0
    private var isUseNumAllWidthGreater = false
    //向右滑动还是向左滑动，向右滑动是正数，向左滑动是负数，数值代表速度
    var speed = -1
    /**
     * direction代表是从左向右排列还是从右向左排列
     */
    var direction: Direction = Direction.RIGHT

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

    private var childOffsetDistence = 0
    private fun doOnLayoutLayout() {
        var showLeft = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            //获取左边偏移量
            val offsetShowLeft = childOffsetDistence + showLeft
            //根据宽度来设置具体的左边偏移量
            val resultShowLeft = if (isUseNumAllWidthGreater) {
                getNumIfAllWidthGreater(offsetShowLeft, allWidth, width)
            } else {
                getNumIfScreenWidthGreater(offsetShowLeft, allWidth, width)
            }
            if (direction == Direction.LEFT) {
                child.layout(
                    paddingLeft + resultShowLeft,
                    0 + paddingTop,
                    paddingLeft + resultShowLeft + childWidth,
                    paddingTop + childHeight
                )
            } else {
                Log.i(TAG, "paddingRight:$paddingRight,left:${paddingRight - resultShowLeft - childWidth}")
                child.layout(
                    right - paddingRight - resultShowLeft - childWidth,
                    0 + paddingTop,
                    right - paddingRight - resultShowLeft,
                    paddingTop + childHeight
                )
            }
            showLeft += childWidth
        }
    }


    //定义动画
    private var animator: ValueAnimator? = null

    private fun startAnimation() {
        if (animator == null) {
            animator = ValueAnimator.ofInt(0, 100)
            animator?.duration = 1000
            animator?.repeatCount = ValueAnimator.INFINITE
            animator?.addUpdateListener {
                if (direction == Direction.LEFT) {
                    childOffsetDistence += speed
                } else {
                    childOffsetDistence -= speed
                }
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

    private fun getView(x: Float): View? {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (x >= child.left && x <= child.right) {
                return child
            }
            Log.i(TAG, "孩子的边距：left：${child.left},right:${child.right}")
        }
        Log.i(TAG, "抬起的xx:$x")
        return null
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        return true
    }


    private var downX: Float = 0f
    private var startTime: Long = 0


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                stopAnimation()
                downX = event.x
                startTime = System.currentTimeMillis()
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = event.x - downX
                downX = event.x
                if (direction == Direction.LEFT) {
                    childOffsetDistence += deltaX.toInt()
                } else {
                    childOffsetDistence -= deltaX.toInt()
                }
                doOnLayoutLayout()
            }
            MotionEvent.ACTION_UP -> {
                val endTime = System.currentTimeMillis()
                if (endTime - startTime <= 100) {
                    val upX = event.x
                    val child = getView(upX)
                    child?.callOnClick()
                }
                startAnimation()

            }
            MotionEvent.ACTION_CANCEL -> {
                startTime = 0
                startAnimation()
            }
        }
        return true
    }

    enum class Direction {
        LEFT,
        RIGHT
    }
}