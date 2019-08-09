package com.eigendaksh.thenewsreader.data.news

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import org.threeten.bp.ZonedDateTime

@Entity(tableName = "news_item", indices = [Index(value = ["title"], unique = true)])
data class NewsItem(

    @PrimaryKey(autoGenerate = true) var itemId: Int = 0,

    var id: Long? = -1,

    var `abstract`: String? = "",

    var section: String = "",

    @field:Json(name = "created_date")
    @ColumnInfo(name = "created_date")
    var createdDate: ZonedDateTime? = ZonedDateTime.now(),

    var multimedia: List<Multimedia>? = emptyList(),

    @field:Json(name = "published_date")
    @ColumnInfo(name = "published_date")
    var publishedDate: ZonedDateTime? = ZonedDateTime.now(),

    var title: String? = "",

    @field:Json(name = "updated_date")
    @ColumnInfo(name = "updated_date")
    var updatedDate: ZonedDateTime? = ZonedDateTime.now(),

    var url: String? = "",

    var type: StoryType = StoryType.NONE,

    var isRead: Boolean = false
)
