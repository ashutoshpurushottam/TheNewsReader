package com.eigendaksh.thenewsreader.repo

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import com.eigendaksh.thenewsreader.data.db.NewsItemDao
import com.eigendaksh.thenewsreader.data.db.PopularNewsItemDao
import com.eigendaksh.thenewsreader.data.news.NewsItem
import com.eigendaksh.thenewsreader.data.news.NewsResponse
import com.eigendaksh.thenewsreader.data.news.StoryType
import com.eigendaksh.thenewsreader.data.popular.PopularNewsItem
import com.eigendaksh.thenewsreader.data.popular.PopularNewsResponse
import com.eigendaksh.thenewsreader.data.preferences.get
import com.eigendaksh.thenewsreader.data.preferences.set
import com.eigendaksh.thenewsreader.networking.NewsNetworkDataSource
import com.eigendaksh.thenewsreader.utils.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

// Don't fetch new stories if it was fetched one hour back
private const val FETCH_WAIT = 60 * 1000 * 60

class NewsRepoImpl(
    private val networkDataSource: NewsNetworkDataSource,
    private val newsItemDao: NewsItemDao,
    private val popularNewsItemDao: PopularNewsItemDao,
    private val prefs: SharedPreferences
) : NewsRepo {

    val TAG by lazy { NewsRepoImpl::class.java.simpleName }


    init {
        networkDataSource.apply {
            downloadedTopStories.observeForever { response: NewsResponse? ->
                persistTopStories(response)
            }

            downloadedMostPopularStories.observeForever { response: PopularNewsResponse? ->
                persistPopularStories(response)
            }

            downloadedBusinessStories.observeForever { response: NewsResponse? ->
                persistBusinessStories(response)
            }

            downloadedSportsStories.observeForever { response: NewsResponse? ->
                persistSportsStories(response)
            }
        }
    }

    fun persistTopStories(response: NewsResponse?) {
        GlobalScope.launch(Dispatchers.IO) {
            newsItemDao.deleteAllTopStories()
            response?.results?.let {
                for(item in it) {
                    item.type = StoryType.TOP_STORY
                    newsItemDao.insert(item)
                }
            }
        }
    }

    fun persistBusinessStories(response: NewsResponse?) {
        GlobalScope.launch(Dispatchers.IO) {
            newsItemDao.deleteAllBusinessStories()
            response?.results?.let {
                for(item in it) {
                    item.type = StoryType.BUSINESS_STORY
                    newsItemDao.insert(item)
                }
            }
        }
    }

    fun persistSportsStories(response: NewsResponse?) {
        GlobalScope.launch(Dispatchers.IO) {
            newsItemDao.deleteAllSportsStories()
            response?.results?.let {
                for(item in it) {
                    item.type = StoryType.SPORTS_STORY
                    newsItemDao.insert(item)
                }
            }
        }
    }

    fun persistPopularStories(response: PopularNewsResponse?) {
        GlobalScope.launch(Dispatchers.IO) {
            popularNewsItemDao.deleteAllPopularStories()
            response?.results?.let {
                popularNewsItemDao.insertAll(it)
            }
        }
    }

    private suspend fun initTopStoriesData() {
        val required = isTopStoriesFetchingRequired()
        Log.d(TAG, "isTopStoriesFetchingRequired: $required")
        if(required) {
            fetchTopStories()
        }
    }

    private suspend fun initBusinessStoriesData() {
        val required = isBusinessStoriesFetchingRequired()
        Log.d(TAG, "isBusinessStoriesFetchingRequired: $required")
        if(required) {
            fetchBusinessStories()
        }
    }

    private suspend fun initSportsStoriesData() {
        val required = isSportsStoriesFetchingRequired()
        Log.d(TAG, "isSportsStoriesFetchingRequired: $required")
        if(required) {
            fetchSportsStories()
        }
    }

    private suspend fun initPopularStoriesData() {
        val required = isPopularStoriesFetchingRequired()
        Log.d(TAG, "isPopularStoriesFetchRequired: $required")
        if(required) {
            fetchPopularStories()
        }
    }

    private suspend fun fetchTopStories() {
        Log.d(TAG, "top stories fetched")
        networkDataSource.getTopStories()
    }

    private suspend fun fetchBusinessStories() {
        Log.d(TAG, "business stories fetched")
        networkDataSource.getBusinessStories()
    }

    private suspend fun fetchSportsStories() {
        Log.d(TAG, "sports stories fetched")
        networkDataSource.getSportsStories()
    }

    private suspend fun fetchPopularStories() {
        Log.d(TAG, "sports stories fetched")
        networkDataSource.getMostPopularStories()
    }

    override suspend fun getTopStories(): LiveData<List<NewsItem>> {
        return withContext(Dispatchers.IO) {
            initTopStoriesData()
            return@withContext newsItemDao.getAllTopStories()
        }
    }

    override suspend fun getBusinessStories(): LiveData<List<NewsItem>> {
        return withContext(Dispatchers.IO) {
            initBusinessStoriesData()
            return@withContext newsItemDao.getAllBusinessStories()
        }
    }

    override suspend fun getSportsStories(): LiveData<List<NewsItem>> {
        return withContext(Dispatchers.IO) {
            initSportsStoriesData()
            return@withContext newsItemDao.getAllSportsStories()
        }
    }


    override suspend fun getPopularStories(): LiveData<List<PopularNewsItem>> {
        return withContext(Dispatchers.IO) {
            initPopularStoriesData()
            return@withContext popularNewsItemDao.getAllPopularStories()
        }
    }

    private fun isTopStoriesFetchingRequired(): Boolean {
        val lastFetchTime = prefs[AppConstants.LAST_FETCH_TIME_TOP_STORY, 0L]
        if(lastFetchTime <= 0) {
            return true
        } else {
            val timeNow = ZonedDateTime.now().toInstant().toEpochMilli()
            return (timeNow - lastFetchTime) > FETCH_WAIT
        }
    }

    private fun isBusinessStoriesFetchingRequired(): Boolean {
        val lastFetchTime = prefs[AppConstants.LAST_FETCH_TIME_BUSINESS_STORY, 0L]
        if(lastFetchTime <= 0) {
            return true
        } else {
            val timeNow = ZonedDateTime.now().toInstant().toEpochMilli()
            return (timeNow - lastFetchTime) > FETCH_WAIT
        }
    }

    private fun isSportsStoriesFetchingRequired(): Boolean {
        val lastFetchTime = prefs[AppConstants.LAST_FETCH_TIME_SPORTS_STORY, 0L]
        if(lastFetchTime <= 0) {
            return true
        } else {
            val timeNow = ZonedDateTime.now().toInstant().toEpochMilli()
            return (timeNow - lastFetchTime) > FETCH_WAIT
        }
    }

    private fun isPopularStoriesFetchingRequired(): Boolean {
        val lastFetchTime = prefs[AppConstants.LAST_FETCH_TIME_POPULAR_STORY, 0L]
        if(lastFetchTime <= 0) {
            return true
        } else {
            val timeNow = ZonedDateTime.now().toInstant().toEpochMilli()
            return (timeNow - lastFetchTime) > FETCH_WAIT
        }
    }

    override fun updateNewsItem(title: String) {
        val item = newsItemDao.getNewsItemFromTitle(title)
        item?.isRead = true
        item?.let {
            newsItemDao.updateItem(it)
        }
    }

    override fun updatePopularNewsItem(title: String) {
        val item = popularNewsItemDao.getNewsItemFromTitle(title)
        item?.isRead = true
        item?.let {
            popularNewsItemDao.updatePopularItem(it)
        }
    }

    override fun deleteAllNewsItem() {
        newsItemDao.deleteAll()
    }


    override fun deleteAllPopularNewsItem() {
        popularNewsItemDao.deleteAllPopularStories()
    }
}