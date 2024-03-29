package com.eigendaksh.thenewsreader.networking

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eigendaksh.thenewsreader.data.news.NewsResponse
import com.eigendaksh.thenewsreader.data.popular.PopularNewsResponse
import com.eigendaksh.thenewsreader.data.preferences.set
import com.eigendaksh.thenewsreader.utils.AppConstants
import org.threeten.bp.ZonedDateTime

class NewsNetworkDataSourceImpl(
    private val service: NyTimesApiService,
    private val prefs: SharedPreferences
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
        if(topStories.results.isNotEmpty()) {
            prefs[AppConstants.LAST_FETCH_TIME_TOP_STORY] = ZonedDateTime.now().toInstant().toEpochMilli()

        }
        _downloadedTopStories.postValue(topStories)
    }



    override suspend fun getMostPopularStories() {
        val popularStories = service.getPopularNews().await()
        if(popularStories.results.isNotEmpty()) {
            prefs[AppConstants.LAST_FETCH_TIME_POPULAR_STORY] = ZonedDateTime.now().toInstant().toEpochMilli()
        }
        _downloadedMostPopularStories.postValue(popularStories)
    }

    override suspend fun getBusinessStories() {
        val businessStories = service.getBusinessNews().await()
        if(businessStories.results.isNotEmpty()) {
            prefs[AppConstants.LAST_FETCH_TIME_BUSINESS_STORY] = ZonedDateTime.now().toInstant().toEpochMilli()

        }
        _downloadedBusinessStories.postValue(businessStories)
    }

    override suspend fun getSportsStories() {
        val sportsStories = service.getSportsNews().await()
        if(sportsStories.results.isNotEmpty()) {
            prefs[AppConstants.LAST_FETCH_TIME_SPORTS_STORY] = ZonedDateTime.now().toInstant().toEpochMilli()
        }
        _downloadedSportsStories.postValue(sportsStories)
    }

}