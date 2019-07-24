package com.eigendaksh.thenewsreader.networking

import com.eigendaksh.thenewsreader.model.news.NewsItem
import com.eigendaksh.thenewsreader.model.news.NewsResponse
import com.eigendaksh.thenewsreader.model.popular.PopularNewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NyTimesApi {

    // https://api.nytimes.com/svc/topstories/v2/home.json?api-key=C0v60kXXPtAEzaF1eYZL8oeBnGWvVlxs
    @GET("topstories/v2/home.json")
    suspend fun getTopStories() : Response<NewsResponse>

    @GET("mostpopular/v2/mostviewed/all-sections/7.json")
    suspend fun getPopularNews(): Response<PopularNewsResponse>

    @GET("topstories/v2/business.json")
    suspend fun getBusinessNews(): Response<NewsResponse>


    @GET("topstories/v2/sports.json")
    suspend fun getSportsNews(): Response<NewsResponse>


}