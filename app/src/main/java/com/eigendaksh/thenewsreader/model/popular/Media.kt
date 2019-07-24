package com.eigendaksh.thenewsreader.model.popular

import com.squareup.moshi.Json

data class Media(
    @Json(name = "media-metadata")
    val mediaMetadata: List<MediaMetadata>
)
