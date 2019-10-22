package com.duyi.horizontalscrollviewdemo.view

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.duyi.horizontalscrollviewdemo.R

class DoubleHorizontalAdapter {
    var doubleHorizontalScrollView:DoubleHorizontalScrollView? = null

    fun getUpItemCount(): Int {
        return 6
    }

    fun getUpItemView(context: Context, position:Int): View {
        val view = View.inflate(context, R.layout.view_item, null)
        view.findViewById<TextView>(R.id.text).text = "哈哈哈$position"
        return view
    }

    fun getDownItemCount(): Int {
        return 6
    }

    fun getDownItemView(context: Context, position:Int): View {
        val view = View.inflate(context, R.layout.view_item, null)
        view.findViewById<TextView>(R.id.text).text = "哈哈哈$position"
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun notifyDataSetChanged() {
        doubleHorizontalScrollView?.notifyDataSetChanged()
    }
}