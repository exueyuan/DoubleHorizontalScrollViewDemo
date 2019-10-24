package com.duyi.horizontalscrollviewdemo.one.view

import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi

abstract class DoubleHorizontalAdapter {
    var doubleHorizontalScrollView:DoubleHorizontalScrollView? = null

    abstract fun getUpItemCount():Int

    abstract fun getUpItemView(context: Context, position:Int): View

    abstract fun getDownItemCount(): Int

    abstract fun getDownItemView(context: Context, position:Int): View

    @RequiresApi(Build.VERSION_CODES.M)
    fun notifyDataSetChanged() {
        doubleHorizontalScrollView?.notifyDataSetChanged()
    }
}