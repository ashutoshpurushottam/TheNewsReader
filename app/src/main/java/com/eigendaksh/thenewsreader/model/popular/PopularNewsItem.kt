package com.eigendaksh.thenewsreader.model.popular

import com.squareup.moshi.Json

data class PopularNewsItem(
    val id: Long,
    val media: List<Media>,
    @Json(name = "published_date")
    val publishedDate: String,
    val title: String,
    val url: String
)
