package com.duyi.horizontalscrollviewdemo.cache.view

import android.view.View
import android.view.ViewGroup

abstract class BaseCacheAutoScrollAdapter {
    abstract fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder

    abstract fun getItemCount(): Int


}

class BaseViewHolder {
    fun getView(view: View) {

    }
}