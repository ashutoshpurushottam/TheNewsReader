package com.eigendaksh.thenewsreader.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eigendaksh.thenewsreader.data.popular.PopularNewsItem

@Dao
interface PopularNewsItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(popularNewsItem: PopularNewsItem)

    @Insert
    fun insertAll(popularNewsItemItemList: List<PopularNewsItem>?)

    @Query("SELECT * from popular_news_item ORDER BY published_date DESC")
    fun getAllPopularStories(): LiveData<List<PopularNewsItem>>

    @Query("DELETE FROM popular_news_item")
    fun deleteAllPopularStories()
}