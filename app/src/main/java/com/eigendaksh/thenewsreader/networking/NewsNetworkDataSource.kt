package com.eigendaksh.thenewsreader.networking

import androidx.lifecycle.LiveData
import com.eigendaksh.thenewsreader.model.news.NewsResponse
import com.eigendaksh.thenewsreader.model.popular.PopularNewsResponse

interface NewsNetworkDataSource {
    val downloadedTopStories: LiveData<NewsResponse>
    val downloadedMostPopularStories: LiveData<PopularNewsResponse>
    val downloadedBusinessStories: LiveData<NewsResponse>
    val downloadedSportsStories: LiveData<NewsResponse>

    suspend fun getTopStories()

    suspend fun getMostPopularStories()

    suspend fun getBusinessStories()

    suspend fun getSportsStories()


}