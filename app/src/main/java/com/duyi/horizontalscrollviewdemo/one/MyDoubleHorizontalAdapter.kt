package com.duyi.horizontalscrollviewdemo.one

import android.content.Context
import android.view.View
import android.widget.TextView
import com.duyi.horizontalscrollviewdemo.R
import com.duyi.horizontalscrollviewdemo.one.view.DoubleHorizontalAdapter

class MyDoubleHorizontalAdapter :DoubleHorizontalAdapter(){
    override fun getUpItemCount(): Int {
        return 8
    }

    override fun getUpItemView(context: Context, position: Int): View {
        val view = View.inflate(context, R.layout.view_item, null)
        view.findViewById<TextView>(R.id.text).text = "哈哈哈$position"
        return view
    }

    override fun getDownItemCount(): Int {
        return 8
    }

    override fun getDownItemView(context: Context, position: Int): View {
        val view = View.inflate(context, R.layout.view_item, null)
        view.findViewById<TextView>(R.id.text).text = "哈哈哈$position"
        return view
    }

}