package com.jamesnyakush.news.data.network

import com.jamesnyakush.news.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country") country:String,
        @Query("apiKey") apiKey:String,
    ) : NewsResponse
}