package com.eigendaksh.thenewsreader.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eigendaksh.thenewsreader.R
import com.eigendaksh.thenewsreader.data.news.NewsItem
import com.eigendaksh.thenewsreader.data.news.StoryType

class NewsItemAdapter(
    itemList: List<NewsItem>,
    listener: NewsItemListener
): GenericAdapter<NewsItem>(itemList, listener) {

    override fun getLayoutId(position: Int, obj: NewsItem): Int {
        return R.layout.news_item
    }

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderFactory.createViewHolder(view, StoryType.TOP_STORY)
    }

}


interface NewsItemListener {
    fun onItemClicked(position: Int)
}