package com.eigendaksh.thenewsreader.repo

import androidx.lifecycle.LiveData
import com.eigendaksh.thenewsreader.model.news.NewsItem
import com.eigendaksh.thenewsreader.model.news.NewsResponse
import com.eigendaksh.thenewsreader.model.popular.PopularNewsItem
import com.eigendaksh.thenewsreader.model.popular.PopularNewsResponse

interface NewsRepo {
    suspend fun getTopStories() : LiveData<List<NewsItem>>
    suspend fun getPopularStories(): LiveData<List<PopularNewsItem>>
    suspend fun getSportsStories(): LiveData<List<NewsItem>>
    suspend fun getBusinessStories(): LiveData<List<NewsItem>>
}