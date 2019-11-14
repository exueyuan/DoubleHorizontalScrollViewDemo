package com.duyi.horizontalscrollviewdemo.autorecycler.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AutoRecyclerViewAdapter<T>(
    private val list: ArrayList<T>,
    private val onBindViewHolderCallback:((holder: RecyclerView.ViewHolder, position: Int, data:T)->Unit)? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var doubleAutoRecyclerAdapter: DoubleAutoRecyclerAdapter<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return doubleAutoRecyclerAdapter.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position % list.size]
        onBindViewHolderCallback?.invoke(holder,position,data)
    }

    override fun getItemViewType(position: Int): Int {
        return doubleAutoRecyclerAdapter.getItemViewType(position)
    }

}