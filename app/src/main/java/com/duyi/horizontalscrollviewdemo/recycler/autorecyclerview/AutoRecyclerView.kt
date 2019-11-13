package com.duyi.horizontalscrollviewdemo.recycler.autorecyclerview

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class AutoRecyclerView : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    fun start() {
        startAnimation()
    }

    fun stop() {
        stopAnimation()
    }

    //定义动画
    private var animator: ValueAnimator? = null

    private fun startAnimation() {
        if (animator == null) {
            animator = ValueAnimator.ofInt(0, 100)
            animator?.duration = 1000
            animator?.repeatCount = ValueAnimator.INFINITE
            animator?.addUpdateListener {
                scrollBy(2, 2)
            }
            animator?.start()
        }
    }

    private fun stopAnimation() {
        animator?.cancel()
        animator = null
    }
}