package com.eigendaksh.thenewsreader.model.news

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import org.threeten.bp.ZonedDateTime

@Entity
data class NewsItem(
    @PrimaryKey val id: Long,
    val `abstract`: String,
    @field:Json(name = "created_date")
    @ColumnInfo(name = "created_date")
    val createdDate: ZonedDateTime,
    val multimedia: List<Multimedia>,
    @field:Json(name = "published_date")
    @ColumnInfo(name = "published_date")
    val publishedDate: ZonedDateTime,
    val title: String,
    @field:Json(name = "updated_date")
    @ColumnInfo(name = "updated_date")
    val updatedDate: ZonedDateTime,
    val url: String
)
