package com.eigendaksh.thenewsreader.data.popular

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "popular_news_item", indices = [Index(value = ["title"], unique = true)])
data class PopularNewsItem(
    @PrimaryKey(autoGenerate = true) var itemId: Int = 0,

    var id: Long? = -1,

    var section: String = "",

    var media: List<Media>? = emptyList(),

    @field:Json(name = "published_date")
    @ColumnInfo(name = "published_date")
    var publishedDate: String? = "",

    var title: String? = "",

    val url: String? = "",

    var isRead: Boolean
)

