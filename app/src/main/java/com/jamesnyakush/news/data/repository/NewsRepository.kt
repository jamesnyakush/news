package com.jamesnyakush.news.data.repository

import com.jamesnyakush.news.data.Response
import com.jamesnyakush.news.data.model.Article
import com.jamesnyakush.news.data.model.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface NewsRepository {

    suspend fun getTopHeadlines(
        country: String,
        apiKey: String
    ): Flow<Response<NewsResponse>>

    suspend fun upsertNews(article: List<Article>)

    fun getSavedArticle(): Flow<List<Article>>
}