package com.eigendaksh.thenewsreader.data.helpers

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.ZonedDateTime


class ZonedDateTimeAdapter {
    @FromJson
    fun fromJson(json: String): ZonedDateTime {
        return ZonedDateTime.parse(json)
    }

    // Outputs the ZonedDateTime as a string
    @ToJson
    fun toJson(value: ZonedDateTime?): String? {
        return if (value != null) value.toString() else null
    }
}