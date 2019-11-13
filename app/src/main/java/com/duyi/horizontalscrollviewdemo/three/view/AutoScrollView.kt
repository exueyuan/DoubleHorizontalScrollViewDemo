package com.duyi.horizontalscrollviewdemo.three.view

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import kotlin.math.abs

class AutoScrollView : RelativeLayout {
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
    var speed = 1

    /**
     * direction代表是从左向右排列还是从右向左排列
     */
    var direction: Direction = Direction.RIGHT

    //设置滚动回调
    var scrollCallBack: ((oldX: Int, newX: Int, changeX: Int) -> Unit)? = null
    //暂停回调
    var stopCallBack: ((isStop: Boolean) -> Unit)? = null

    var isStop = false
        set(value) {
            field = value
            if (isStop) {
                stopAnimation(false)
            } else {
                startAnimation(false)
            }
        }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        //所有元素的长度
        allWidth = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            allWidth += getMeasuredMarginWidth(child)
        }
        if (childCount > 0) {
            isUseNumAllWidthGreater = width <= (allWidth - getChildAt(childCount - 1).measuredWidth)
        }
        doOnLayoutLayout()

        startAnimation()
    }

    fun setDistenceChange(x: Int) {
        childOffsetDistence += x
        doOnLayoutLayout()
    }

    fun setDistence(distence: Int) {
        childOffsetDistence = distence
        doOnLayoutLayout()
    }


    private var childOffsetDistence = 0
    private fun doOnLayoutLayout() {
        var showLeft = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childWidth = getMeasuredMarginWidth(child)
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
                val left = paddingLeft + child.marginLeft + resultShowLeft
                val right = left + child.measuredWidth
                val top = child.marginTop + paddingTop
                val bottom = top + child.measuredHeight
                child.layout(left, top, right, bottom)
            } else {
                Log.i(TAG, "paddingRight:$paddingRight,left:${paddingRight - resultShowLeft - childWidth}")
                val left = right - paddingRight - resultShowLeft - childWidth + child.marginLeft
                val right = left + child.measuredWidth
                val top = child.marginTop + paddingTop
                val bottom = top + child.measuredHeight
                child.layout(left, top, right, bottom)
            }
            showLeft += childWidth
        }
    }


    //定义动画
    private var animator: ValueAnimator? = null

    private fun startAnimation(isChangeIsStop:Boolean = true) {
        if (isChangeIsStop) {
            isStop = false
            stopCallBack?.invoke(isStop)
        }
        if (animator == null) {
            animator = ValueAnimator.ofInt(0, 100)
            animator?.duration = 1000
            animator?.repeatCount = ValueAnimator.INFINITE
            animator?.addUpdateListener {
                val oldChildOffsetDistence = childOffsetDistence
                if (direction == Direction.LEFT) {
                    childOffsetDistence += speed
                } else {
                    childOffsetDistence -= speed
                }
                val newChildOffsetDistence = childOffsetDistence
                scrollCallBack?.invoke(oldChildOffsetDistence, newChildOffsetDistence, newChildOffsetDistence - oldChildOffsetDistence)
                doOnLayoutLayout()
            }
            animator?.start()
        }
    }

    private fun stopAnimation(isChangeIsStop:Boolean = true) {
        if (isChangeIsStop) {
            isStop = true
            stopCallBack?.invoke(isStop)
        }
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
        if (allLength == 0) {
            return 0
        }
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


    private fun getMeasuredMarginWidth(view: View): Int {
        return view.measuredWidth + view.marginLeft + view.marginRight
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        return true
    }


    private var downX: Float = 0f
    private var startDownX: Float = 0f
    private var isCallClick = false


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        requestDisallowInterceptTouchEvent(true)
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                stopAnimation()
                downX = event.x
                startDownX = event.x
                isCallClick = true
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = event.x - downX
                if (abs(event.x - startDownX) > 30) {
                    isCallClick = false
                }
                downX = event.x
                val oldChildOffsetDistence = childOffsetDistence
                if (direction == Direction.LEFT) {
                    childOffsetDistence += deltaX.toInt()
                } else {
                    childOffsetDistence -= deltaX.toInt()
                }
                val newChildOffsetDistence = childOffsetDistence
                scrollCallBack?.invoke(oldChildOffsetDistence, newChildOffsetDistence, newChildOffsetDistence)
                doOnLayoutLayout()
            }
            MotionEvent.ACTION_UP -> {
                if (isCallClick) {
                    val upX = event.x
                    val child = getView(upX)
                    child?.callOnClick()
                }
                startAnimation()

            }
            MotionEvent.ACTION_CANCEL -> {
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