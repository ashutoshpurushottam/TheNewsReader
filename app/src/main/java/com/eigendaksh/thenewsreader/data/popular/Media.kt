package com.eigendaksh.thenewsreader.data.popular

import com.eigendaksh.thenewsreader.data.news.Multimedia
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media(
    @field:Json(name = "media-metadata")
    val mediaMetadata: List<Multimedia>
)
