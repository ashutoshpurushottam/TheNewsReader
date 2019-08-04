package com.eigendaksh.thenewsreader.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class GenericAdapter<T>(
    private val itemList: List<T>,
    private val listener: NewsItemListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
            , viewType)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(itemList[position], listener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, itemList[position])
    }


    protected abstract fun getLayoutId(position: Int, obj: T): Int
    abstract fun getViewHolder(view: View, viewType: Int):RecyclerView.ViewHolder


    internal interface Binder<T> {
        fun bind(data: T, listener: NewsItemListener)
    }
}