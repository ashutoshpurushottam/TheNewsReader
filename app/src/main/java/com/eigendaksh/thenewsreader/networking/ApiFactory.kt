package com.eigendaksh.thenewsreader.networking

import android.util.Log
import com.eigendaksh.thenewsreader.data.helpers.StoryTypeAdapter
import com.eigendaksh.thenewsreader.data.helpers.ZonedDateTimeAdapter
import com.eigendaksh.thenewsreader.internal.CoroutineCallAdapterFactory
import com.eigendaksh.thenewsreader.utils.AppConstants
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    // authInterceptor to append api-key query in all the requests.
    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api-key", AppConstants.API_KEY)
            .build()

        Log.i("ApiFactory", newUrl.toString())

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val nyTimesClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS).build()

    // Add adapter for Moshi
    private val userMoshi = Moshi
        .Builder()
        .add(ZonedDateTimeAdapter())
        .add(StoryTypeAdapter())
        .build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(nyTimesClient)
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(userMoshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val service: NyTimesApiService = retrofit().create(NyTimesApiService::class.java)
}