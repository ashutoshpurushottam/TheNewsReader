package com.eigendaksh.thenewsreader.data.news

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Multimedia(
    val height: Int,
    val width: Int,
    val url: String
)