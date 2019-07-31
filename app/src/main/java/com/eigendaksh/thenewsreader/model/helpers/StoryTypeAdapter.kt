package com.eigendaksh.thenewsreader.model.helpers

import com.eigendaksh.thenewsreader.model.news.StoryType
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
    fun toJson(value: ZonedDateTime?): String? {
        return if (value != null) value.toString() else null
    }
}