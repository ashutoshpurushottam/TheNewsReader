package com.eigendaksh.thenewsreader.data.preferences

typealias PreferenceObserver<T> = (key: String, value: T) -> Unit