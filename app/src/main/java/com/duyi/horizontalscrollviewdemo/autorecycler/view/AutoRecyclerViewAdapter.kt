package com.duyi.horizontalscrollviewdemo.autorecycler.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duyi.horizontalscrollviewdemo.autorecycler.MyData

class AutoRecyclerViewAdapter<T>(private val list: List<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.getHolder(parent.context)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position % list.size]
        if (holder is ViewHolder && data is MyData) {
            holder.onBindViewHolder(data, position)
        }
    }

}