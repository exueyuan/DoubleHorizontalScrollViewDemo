package com.duyi.horizontalscrollviewdemo.recycler.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duyi.horizontalscrollviewdemo.R


public class AutoPollAdapter(private val mData: List<String>) :
    RecyclerView.Adapter<AutoPollAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_auto_poll, parent, false)
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val data = mData[position % mData.size]
        holder.tv.text = data
    }

    override fun getItemCount(): Int {
        return Integer.MAX_VALUE
    }

    inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView = itemView.findViewById(R.id.tv_content)
    }
}