package com.duyi.horizontalscrollviewdemo.autorecycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duyi.horizontalscrollviewdemo.autorecycler.holder.ViewHolder
import com.duyi.horizontalscrollviewdemo.autorecycler.view.DoubleAutoRecyclerAdapter

class MyAdapter : DoubleAutoRecyclerAdapter<BaseData>() {
    var topList = arrayListOf<BaseData>()
    var bottomList = arrayListOf<BaseData>()
    override fun getTopList(): List<BaseData> {
        return topList
    }

    override fun getBottomList(): List<BaseData> {
        return bottomList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.getHolder(parent.context)
    }

    override fun onTopBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        data: BaseData
    ) {
        if (holder is ViewHolder && data is MyData) {
            holder.onBindViewHolder(data, position)
        }
    }

    override fun onBottomBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        data: BaseData
    ) {
        if (holder is ViewHolder && data is MyData) {
            holder.onBindViewHolder(data, position)
        }
    }

}