package com.eigendaksh.thenewsreader.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eigendaksh.thenewsreader.data.helpers.Converters
import com.eigendaksh.thenewsreader.data.news.NewsItem
import com.eigendaksh.thenewsreader.data.popular.PopularNewsItem

@Database(entities = [NewsItem::class, PopularNewsItem::class], version = 5, exportSchema = false )
@TypeConverters(Converters::class)
abstract class LocalNewsDb : RoomDatabase() {

    abstract fun newsItemDao(): NewsItemDao
    abstract fun popularNewsItemDao(): PopularNewsItemDao

    companion object {
        @Volatile
        private var INSTANCE: LocalNewsDb? = null

        fun getInstance(context: Context): LocalNewsDb = INSTANCE?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                LocalNewsDb::class.java,
                "news_db")
                .fallbackToDestructiveMigration()//remote sources more reliable
                .build().also { INSTANCE=it }
        }
    }
}