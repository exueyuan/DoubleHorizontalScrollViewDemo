package com.duyi.horizontalscrollviewdemo.autorecycler.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class DoubleAutoRecyclerAdapter<T> {
    var doubleAutoRecyclerView: DoubleAutoRecyclerView<T>? = null

    abstract fun getTopList():List<T>

    abstract fun getBottomList():List<T>

    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun onTopBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, data: T)

    abstract fun onBottomBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, data: T)

    open fun getItemViewType(position: Int): Int {
        return 0
    }
}