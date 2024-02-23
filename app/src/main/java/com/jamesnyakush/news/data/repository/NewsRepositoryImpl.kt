package com.jamesnyakush.news.data.repository

import com.jamesnyakush.news.data.Response
import com.jamesnyakush.news.data.model.NewsResponse
import com.jamesnyakush.news.data.network.ApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl constructor(
    private var apiClient: ApiClient
) : NewsRepository {
    override suspend fun getTopHeadlines(
        country: String, apiKey: String
    ): Flow<Response<NewsResponse>> = flow {
        try {
            emit(Response.Loading)
            val result = apiClient.getNews(country = country, apiKey = apiKey)
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }
}