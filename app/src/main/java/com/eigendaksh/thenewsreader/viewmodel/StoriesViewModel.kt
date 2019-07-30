package com.eigendaksh.thenewsreader.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eigendaksh.thenewsreader.model.news.NewsResponse
import com.eigendaksh.thenewsreader.model.popular.PopularNewsResponse
import com.eigendaksh.thenewsreader.networking.ApiFactory
import kotlinx.coroutines.launch

class StoriesViewModel(application: Application): AndroidViewModel(application) {

    val observableTopStories: MutableLiveData<NewsResponse?>?
    val observablePopularStories: MutableLiveData<PopularNewsResponse?>?
    val observableSportsStories: MutableLiveData<NewsResponse?>?

    init {
        observableTopStories = MutableLiveData()
        observablePopularStories = MutableLiveData()
        observableSportsStories = MutableLiveData()
    }


    fun fetchTopStories() {
        viewModelScope.launch {
            observableTopStories?.postValue(ApiFactory.nyTimesApi.getTopStories().body())
        }
    }

    fun fetchPopularStories() {
        viewModelScope.launch {
            observablePopularStories?.postValue(ApiFactory.nyTimesApi.getPopularNews().body())

        }
    }

    fun fetchSportsStories() {
        viewModelScope.launch {

        }
    }

}