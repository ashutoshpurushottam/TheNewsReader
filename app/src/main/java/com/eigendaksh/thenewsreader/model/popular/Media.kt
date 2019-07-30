package com.eigendaksh.thenewsreader.model.popular

import com.squareup.moshi.Json

data class Media(
    @field:Json(name = "media-metadata")
    val mediaMetadata: List<MediaMetadata>
)
