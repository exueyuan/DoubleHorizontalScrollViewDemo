package com.duyi.horizontalscrollviewdemo.autorecycler.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

class AutoRecyclerView : RecyclerView {
    companion object {
        private const val TIME_AUTO_POLL: Long = 16
    }

    var autoPollTask: AutoPollTask

    init {
        autoPollTask = AutoPollTask(this)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )




    var speed = -2

    var canRun = false
    var isRunning = false
        set(value) {
            field = value
            start()
        }

    //开启:如果正在运行,先停止->再开启
    fun start() {
        if (isRunning) {
            stop()
        }
        canRun = true
        isRunning = true
        postDelayed(autoPollTask, TIME_AUTO_POLL)
    }

    fun stop() {
        isRunning = false
        removeCallbacks(autoPollTask)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                if (isRunning) {
                    stop()
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_OUTSIDE -> {
                if (canRun) {
                    start()
                }
            }
        }
        return super.onTouchEvent(e)
    }


    //使用弱引用持有外部类引用->防止内存泄漏
    class AutoPollTask(reference: AutoRecyclerView) : Runnable {
        private val mReference: WeakReference<AutoRecyclerView> = WeakReference(reference)
        override fun run() {
            val recyclerView = mReference.get()
            if (recyclerView != null && recyclerView.isRunning && recyclerView.canRun) {
                recyclerView.scrollBy(recyclerView.speed, recyclerView.speed)
                recyclerView.postDelayed(recyclerView.autoPollTask, TIME_AUTO_POLL)
            }
        }
    }


    override fun onDetachedFromWindow() {
        stop()
        super.onDetachedFromWindow()
    }

}