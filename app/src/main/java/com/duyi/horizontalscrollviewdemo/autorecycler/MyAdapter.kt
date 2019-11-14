package com.duyi.horizontalscrollviewdemo.autorecycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duyi.horizontalscrollviewdemo.autorecycler.holder.ViewHolder
import com.duyi.horizontalscrollviewdemo.autorecycler.view.DoubleAutoRecyclerAdapter

class MyAdapter(val dataList:List<BaseData>) : DoubleAutoRecyclerAdapter<BaseData>() {

    var topList = arrayListOf<BaseData>()
    var bottomList = arrayListOf<BaseData>()
    init {
        initData()
    }

    private fun initData() {
        topList.clear()
        bottomList.clear()
        if (dataList.isNotEmpty()){
            val topNum = if (dataList.size % 2 == 0) {
                dataList.size / 2
            } else {
                dataList.size / 2 + 1
            }
            topList.addAll(dataList.subList(0, topNum))
            if (dataList.size > topNum) {
                bottomList.addAll(dataList.subList(topNum, dataList.size))
            }
        }
    }

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


    override fun notifyDataSetChanged() {
        initData()
        super.notifyDataSetChanged()
    }
}