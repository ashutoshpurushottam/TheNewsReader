package com.eigendaksh.thenewsreader.base

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class NewsReaderApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}