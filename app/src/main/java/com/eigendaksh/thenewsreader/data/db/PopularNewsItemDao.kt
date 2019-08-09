package com.eigendaksh.thenewsreader.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eigendaksh.thenewsreader.data.news.NewsItem
import com.eigendaksh.thenewsreader.data.popular.PopularNewsItem

@Dao
interface PopularNewsItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(popularNewsItem: PopularNewsItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(popularNewsItemItemList: List<PopularNewsItem>?)

    @Query("SELECT * from popular_news_item ORDER BY published_date DESC")
    fun getAllPopularStories(): LiveData<List<PopularNewsItem>>

    @Query("DELETE FROM popular_news_item")
    fun deleteAllPopularStories()

    @Query("SELECT * FROM popular_news_item WHERE title = :title")
    fun getNewsItemFromTitle(title: String): PopularNewsItem?

    @Update
    fun updatePopularItem(vararg popularNewsItem: PopularNewsItem)

}