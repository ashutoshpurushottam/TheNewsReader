package com.eigendaksh.thenewsreader.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eigendaksh.thenewsreader.R
import com.eigendaksh.thenewsreader.data.news.StoryType
import com.eigendaksh.thenewsreader.data.popular.PopularNewsItem

class PopularNewsItemAdapter(
    itemList: List<PopularNewsItem>,
    listener: NewsItemListener
): GenericAdapter<PopularNewsItem>(itemList, listener) {

    override fun getLayoutId(position: Int, obj: PopularNewsItem): Int {
        return R.layout.news_item
    }

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderFactory.createViewHolder(view, StoryType.POPULAR_STORY)
    }

}

