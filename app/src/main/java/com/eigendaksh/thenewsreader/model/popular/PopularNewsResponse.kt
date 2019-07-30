package com.eigendaksh.thenewsreader.model.popular

import com.squareup.moshi.Json


data class PopularNewsResponse(
    @field:Json(name = "num_results")
    val numResults: Int,
    val results: List<PopularNewsItem>
)