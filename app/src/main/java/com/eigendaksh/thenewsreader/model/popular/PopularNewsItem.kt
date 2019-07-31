package com.eigendaksh.thenewsreader.model.popular

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import org.threeten.bp.ZonedDateTime

@Entity(tableName = "popular_news_item")
data class PopularNewsItem(
    @PrimaryKey(autoGenerate = true) var itemId: Int = 0,

    var id: Long? = -1,

    var media: List<Media>? = emptyList(),

    @field:Json(name = "published_date")
    @ColumnInfo(name = "published_date")
    var publishedDate: String? = "",

    var title: String? = "",

    val url: String? = ""
)
