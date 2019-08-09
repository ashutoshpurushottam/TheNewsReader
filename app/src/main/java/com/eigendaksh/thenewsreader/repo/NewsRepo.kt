package com.eigendaksh.thenewsreader.repo

import androidx.lifecycle.LiveData
import com.eigendaksh.thenewsreader.data.news.NewsItem
import com.eigendaksh.thenewsreader.data.popular.PopularNewsItem

interface NewsRepo {
    suspend fun getTopStories() : LiveData<List<NewsItem>>
    suspend fun getPopularStories(): LiveData<List<PopularNewsItem>>
    suspend fun getSportsStories(): LiveData<List<NewsItem>>
    suspend fun getBusinessStories(): LiveData<List<NewsItem>>
    fun updateNewsItem(title: String)
    fun updatePopularNewsItem(title: String)
    fun deleteAllNewsItem()
    fun deleteAllPopularNewsItem()
}