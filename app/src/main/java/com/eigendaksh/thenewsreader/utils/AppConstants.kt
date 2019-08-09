package com.eigendaksh.thenewsreader.utils

import com.eigendaksh.thenewsreader.BuildConfig

object AppConstants {
    const val API_KEY = BuildConfig.NYTIMES_API_KEY
    const val BASE_URL = "https://api.nytimes.com/svc/"
    const val LAST_FETCH_TIME_TOP_STORY = "LAST_FETCH_TIME_TOP_STORY"
    const val LAST_FETCH_TIME_BUSINESS_STORY = "LAST_FETCH_TIME_BUSINESS_STORY"
    const val LAST_FETCH_TIME_SPORTS_STORY = "LAST_FETCH_TIME_SPORTS_STORY"
    const val LAST_FETCH_TIME_POPULAR_STORY = "LAST_FETCH_TIME_POPULAR_STORY"

    const val URL_KEY = "url_key"
    const val TITLE_KEY = "title_key"
    const val IS_POPULAR_KEY = "is_popular_key"
    const val IS_READ_KEY = "is_read_key"
}