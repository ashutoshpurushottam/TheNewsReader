package com.eigendaksh.thenewsreader.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eigendaksh.thenewsreader.data.news.NewsItem

@Dao
interface NewsItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newsItem: NewsItem)

    @Insert
    fun insertAll(newsItemList: List<NewsItem>?)

    @Query("SELECT * from news_item WHERE type = 'TOP_STORY' ORDER BY published_date DESC")
    fun getAllTopStories(): LiveData<List<NewsItem>>

    @Query("SELECT * from news_item WHERE type = 'BUSINESS_STORY' ORDER BY published_date DESC")
    fun getAllBusinessStories(): LiveData<List<NewsItem>>

    @Query("SELECT * from news_item WHERE type = 'SPORTS_STORY' ORDER BY published_date DESC")
    fun getAllSportsStories(): LiveData<List<NewsItem>>

    @Query("DELETE FROM news_item")
    fun deleteAll()

    @Query("DELETE FROM news_item WHERE type = 'TOP_STORY'")
    fun deleteAllTopStories()

    @Query("DELETE FROM news_item WHERE type = 'BUSINESS_STORY'")
    fun deleteAllBusinessStories()

    @Query("DELETE FROM news_item WHERE type = 'SPORTS_STORY'")
    fun deleteAllSportsStories()

}