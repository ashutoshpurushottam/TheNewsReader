package com.eigendaksh.thenewsreader.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eigendaksh.thenewsreader.data.news.NewsResponse
import com.eigendaksh.thenewsreader.data.popular.PopularNewsResponse

class NewsNetworkDataSourceImpl(
    private val service: NyTimesApiService
) : NewsNetworkDataSource {

    private val _downloadedTopStories = MutableLiveData<NewsResponse>()
    override val downloadedTopStories: LiveData<NewsResponse>
        get() = _downloadedTopStories

    private val _downloadedMostPopularStories = MutableLiveData<PopularNewsResponse>()
    override val downloadedMostPopularStories: LiveData<PopularNewsResponse>
        get() = _downloadedMostPopularStories

    private val _downloadedBusinessStories = MutableLiveData<NewsResponse>()
    override val downloadedBusinessStories: LiveData<NewsResponse>
        get() = _downloadedBusinessStories

    private val _downloadedSportsStories = MutableLiveData<NewsResponse>()
    override val downloadedSportsStories: LiveData<NewsResponse>
        get() = _downloadedSportsStories

    override suspend fun getTopStories() {
        val topStories = service.getTopStories().await()
        _downloadedTopStories.postValue(topStories)
    }

    override suspend fun getMostPopularStories() {
        val popularStories = service.getPopularNews().await()
        _downloadedMostPopularStories.postValue(popularStories)
    }

    override suspend fun getBusinessStories() {
        val businessStories = service.getBusinessNews().await()
        _downloadedBusinessStories.postValue(businessStories)
    }

    override suspend fun getSportsStories() {
        val sportsStories = service.getSportsNews().await()
        _downloadedSportsStories.postValue(sportsStories)
    }

}