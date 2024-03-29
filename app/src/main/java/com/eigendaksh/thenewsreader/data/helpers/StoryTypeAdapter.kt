package com.eigendaksh.thenewsreader.data.helpers

import com.eigendaksh.thenewsreader.data.news.StoryType
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.ZonedDateTime

class StoryTypeAdapter {
    @FromJson
    fun fromJson(json: String): StoryType {
        return StoryType.valueOf(json)
    }

    // Outputs the ZonedDateTime as a string
    @ToJson
    fun toJson(value: StoryType?): String? {
        return if (value != null) value.toString() else null
    }
}