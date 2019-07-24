package com.eigendaksh.thenewsreader.model.news

import com.squareup.moshi.Json

data class NewsItem(
    val id: Long,
    val `abstract`: String,
    @Json(name = "created_date")
    val createdDate: String,
    val multimedia: List<Multimedia>,
    @Json(name = "published_date")
    val publishedDate: String,
    val title: String,
    @Json(name = "updated_date")
    val updatedDate: String,
    val url: String
)
