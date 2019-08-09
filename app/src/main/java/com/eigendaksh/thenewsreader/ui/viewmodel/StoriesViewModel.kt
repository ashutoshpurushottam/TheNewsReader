package com.eigendaksh.thenewsreader.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.eigendaksh.thenewsreader.data.db.LocalNewsDb
import com.eigendaksh.thenewsreader.data.news.NewsItem
import com.eigendaksh.thenewsreader.data.popular.PopularNewsItem
import com.eigendaksh.thenewsreader.data.preferences.PreferenceHelper
import com.eigendaksh.thenewsreader.networking.ApiFactory
import com.eigendaksh.thenewsreader.networking.NewsNetworkDataSourceImpl
import com.eigendaksh.thenewsreader.repo.NewsRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoriesViewModel(application: Application) : AndroidViewModel(application) {

    private val newsRepoImpl: NewsRepoImpl = NewsRepoImpl(
        NewsNetworkDataSourceImpl(ApiFactory.service, PreferenceHelper.defaultPref(application)),
        LocalNewsDb.getInstance(application).newsItemDao(),
        LocalNewsDb.getInstance(application).popularNewsItemDao(),
        PreferenceHelper.defaultPref(application)
    )

    val observableTopStories: MutableLiveData<List<NewsItem>>? = MutableLiveData()
    val observableBusinessStories: MutableLiveData<List<NewsItem>>? = MutableLiveData()
    val observableSportsStories: MutableLiveData<List<NewsItem>>? = MutableLiveData()
    val observablePopularStories: MutableLiveData<List<PopularNewsItem>>? = MutableLiveData()

    fun fetchTopStories() {
        viewModelScope.launch {
            newsRepoImpl.getTopStories().observeForever(Observer {
                observableTopStories?.postValue(it)
            })
        }
    }

    fun fetchBusinessStories() {
        viewModelScope.launch {
            newsRepoImpl.getBusinessStories().observeForever(Observer {
                observableBusinessStories?.postValue(it)
            })
        }
    }

    fun fetchSportsStories() {
        viewModelScope.launch {
            newsRepoImpl.getSportsStories().observeForever(Observer {
                observableSportsStories?.postValue(it)
            })
        }
    }

    fun fetchPopularStories() {
        viewModelScope.launch {
            newsRepoImpl.getPopularStories().observeForever(Observer {
                observablePopularStories?.postValue(it)
            })
        }
    }

     fun updateNewsItem(title: String) {
         viewModelScope.launch(Dispatchers.IO) {
             newsRepoImpl.updateNewsItem(title)
         }
     }

    fun updatePopularNewsItem(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepoImpl.updatePopularNewsItem(title)
        }
    }
}