package com.eigendaksh.thenewsreader.model.preferences

typealias PreferenceObserver<T> = (key: String, value: T) -> Unit