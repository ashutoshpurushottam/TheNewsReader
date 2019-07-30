package com.eigendaksh.thenewsreader.model.news

import com.eigendaksh.thenewsreader.model.helpers.ZonedDateTimeAdapter
import com.squareup.moshi.Json
import org.threeten.bp.ZonedDateTime

data class NewsResponse(
    @field:Json(name = "last_updated")
    val lastUpdated: ZonedDateTime,
    @field:Json(name = "num_results")
    val numResults: Int,
    val results: List<NewsItem>
)
