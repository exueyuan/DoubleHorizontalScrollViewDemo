package com.duyi.horizontalscrollviewdemo.autorecycler.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.duyi.horizontalscrollviewdemo.R
import com.duyi.horizontalscrollviewdemo.autorecycler.MyData

class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    companion object {
        private const val layoutId = R.layout.view_item

        fun getHolder(context: Context): RecyclerView.ViewHolder {
            return ViewHolder(
                View.inflate(
                    context,
                    layoutId,
                    null
                )
            )
        }
    }

    fun onBindViewHolder(
        data: MyData,
        position: Int
    ) {
        val text = itemView.findViewById<TextView>(R.id.text)
        text.text = data.str + "::::::"+position
        itemView.setOnClickListener {
            Toast.makeText(itemView.context, data.str + "::::::"+position, Toast.LENGTH_SHORT).show()
        }
    }
}