package com.duyi.horizontalscrollviewdemo.three.view

import android.content.Context
import android.view.View

abstract class DoubleAutoScrollAdapter {
    var doubleAutoScrollView: DoubleAutoScrollView? = null

    abstract fun getTopItemCount():Int

    abstract fun getTopItemView(context: Context, position:Int): View

    abstract fun getDownItemCount(): Int

    abstract fun getDownItemView(context: Context, position:Int): View

    open fun notifyDataSetChanged() {
        doubleAutoScrollView?.notifyDataSetChanged()
    }
}