package com.eigendaksh.thenewsreader.model.helpers

import androidx.room.TypeConverter
import com.eigendaksh.thenewsreader.model.news.Multimedia
import com.eigendaksh.thenewsreader.model.news.MultimediaJsonAdapter
import com.eigendaksh.thenewsreader.model.news.StoryType
import com.eigendaksh.thenewsreader.model.popular.Media
import com.squareup.moshi.*
import org.threeten.bp.ZonedDateTime

class Converters {

    @TypeConverter
    fun toMediaList(json: String) : List<Media>? {
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Media::class.java)
        val adapter: JsonAdapter<List<Media>> = moshi.adapter(listType)
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun fromMediaJson(stat: List<Media>) : String {
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Media::class.java)
        val adapter: JsonAdapter<List<Media>> = moshi.adapter(listType)
        return adapter.toJson(stat)
    }

    @TypeConverter
    fun toMultimediaList(json: String) : List<Multimedia>? {
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Multimedia::class.java)
        val adapter: JsonAdapter<List<Multimedia>> = moshi.adapter(listType)
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun fromMultimediaJson(stat: List<Multimedia>) : String {
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Multimedia::class.java)
        val adapter: JsonAdapter<List<Multimedia>> = moshi.adapter(listType)
        return adapter.toJson(stat)
    }

    @TypeConverter
    fun fromJson(json: String): ZonedDateTime {
        return ZonedDateTime.parse(json)
    }

    // Outputs the ZonedDateTime as a string
    @TypeConverter
    fun toJson(value: ZonedDateTime?): String? {
        return if (value != null) value.toString() else null
    }

    @TypeConverter
    fun toStoryType(json: String): StoryType {
        return StoryType.valueOf(json)
    }

    @TypeConverter
    fun fromStoryType(value: StoryType?): String? {
        return if (value != null) value.toString() else null
    }

}