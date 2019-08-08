package com.eigendaksh.thenewsreader.ui.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eigendaksh.thenewsreader.R
import com.eigendaksh.thenewsreader.data.news.NewsItem
import com.eigendaksh.thenewsreader.data.news.StoryType
import com.eigendaksh.thenewsreader.data.popular.PopularNewsItem
import org.threeten.bp.format.DateTimeFormatter


val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")

object ViewHolderFactory {

    fun createViewHolder(view: View, type: StoryType): RecyclerView.ViewHolder {
        return when(type) {
            StoryType.POPULAR_STORY -> PopularNewsViewHolder(view)
            else -> NewsViewHolder(view)
        }
    }

    class NewsViewHolder(
        itemView: View
    ) :  RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<NewsItem> {

        var titleView: TextView = itemView.findViewById(R.id.title)
        var thumbnailView: ImageView = itemView.findViewById(R.id.thumbnail)
        var dateView: TextView = itemView.findViewById(R.id.date)
        var sectionView: TextView = itemView.findViewById(R.id.section)

        override fun bind(data: NewsItem, listener: NewsItemListener) {
            titleView.text = data.title
            sectionView.text = data.section

            var url = ""
            try {
                url = data.multimedia?.get(0)?.url ?: ""
            } catch (e: IndexOutOfBoundsException) {
                Log.w("ViewHolderFactory", e.localizedMessage ?: "")
            }

            Glide.with(itemView.context).load(url).placeholder(R.drawable.ic_news).into(thumbnailView)
            dateView.text = data.publishedDate?.format(formatter)

            itemView.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }
        }
    }

    class PopularNewsViewHolder(
        itemView: View
    ) :  RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<PopularNewsItem> {

        var titleView: TextView = itemView.findViewById(R.id.title)
        var thumbnailView: ImageView = itemView.findViewById(R.id.thumbnail)
        var dateView: TextView = itemView.findViewById(R.id.date)
        var sectionView: TextView = itemView.findViewById(R.id.section)

        override fun bind(data: PopularNewsItem, listener: NewsItemListener) {
            titleView.text = data.title
            sectionView.text = data.section

            val url = data.media?.get(0)?.mediaMetadata?.get(0)?.url ?: ""
            Glide.with(itemView.context).load(url).placeholder(R.drawable.ic_news).into(thumbnailView)
            dateView.text = data.publishedDate?.format(formatter)

            itemView.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }
        }
    }

}