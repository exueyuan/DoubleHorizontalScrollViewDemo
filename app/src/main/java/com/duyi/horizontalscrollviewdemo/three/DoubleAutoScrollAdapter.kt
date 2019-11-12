package com.duyi.horizontalscrollviewdemo.three

import android.content.Context
import android.view.View

abstract class DoubleAutoScrollAdapter {
    var doubleAutoScrollView: DoubleAutoScrollView? = null

    abstract fun getTopItemCount():Int

    abstract fun getTopItemView(context: Context, position:Int): View

    abstract fun getDownItemCount(): Int

    abstract fun getDownItemView(context: Context, position:Int): View

    fun notifyDataSetChanged() {
        doubleAutoScrollView?.notifyDataSetChanged()
    }
}