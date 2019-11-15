package com.duyi.horizontalscrollviewdemo.three

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.duyi.horizontalscrollviewdemo.R
import com.duyi.horizontalscrollviewdemo.three.view.DoubleAutoScrollAdapter

class MyAutoHorizontalAdapter :
    DoubleAutoScrollAdapter(){
    override fun getTopItemCount(): Int {
        return 8
    }

    override fun getTopItemView(context: Context, position: Int, cacheView:View?): View {
        val view = cacheView ?: View.inflate(context, R.layout.view_item_three, null)
        view.findViewById<TextView>(R.id.text).text = "哈哈哈$position"
        view.setOnClickListener {
            Toast.makeText(context, "哈哈哈$position", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    override fun getDownItemCount(): Int {
        return 10
    }

    override fun getDownItemView(context: Context, position: Int, cacheView:View?): View {
        val view = cacheView ?: View.inflate(context, R.layout.view_item_three, null)
        view.findViewById<TextView>(R.id.text).text = "哈哈哈$position"
        view.setOnClickListener {
            Toast.makeText(context, "哈哈哈$position", Toast.LENGTH_SHORT).show()
        }
        return view
    }

}