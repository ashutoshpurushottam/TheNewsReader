package com.eigendaksh.thenewsreader.networking

import com.eigendaksh.thenewsreader.model.news.NewsItem
import com.eigendaksh.thenewsreader.model.news.NewsResponse
import com.eigendaksh.thenewsreader.model.popular.PopularNewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface NyTimesApiService {

    // https://api.nytimes.com/svc/topstories/v2/home.json?api-key=C0v60kXXPtAEzaF1eYZL8oeBnGWvVlxs
    @GET("topstories/v2/home.json")
    fun getTopStories() : Deferred<NewsResponse>

    @GET("mostpopular/v2/mostviewed/all-sections/7.json")
    fun getPopularNews(): Deferred<PopularNewsResponse>

    @GET("topstories/v2/business.json")
    fun getBusinessNews(): Deferred<NewsResponse>


    @GET("topstories/v2/sports.json")
    fun getSportsNews(): Deferred<NewsResponse>

}