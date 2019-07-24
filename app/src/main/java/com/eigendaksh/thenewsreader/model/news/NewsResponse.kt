package com.eigendaksh.thenewsreader.model.news

import com.squareup.moshi.Json

data class NewsResponse(
    @Json(name = "last_updated")
    val lastUpdated: String,
    @Json(name = "num_results")
    val numResults: Int,
    val results: List<NewsItem>
)
