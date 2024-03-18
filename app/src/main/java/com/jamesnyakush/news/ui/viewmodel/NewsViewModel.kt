package com.jamesnyakush.news.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamesnyakush.news.Payload
import com.jamesnyakush.news.data.Response
import com.jamesnyakush.news.data.model.Article
import com.jamesnyakush.news.data.model.NewsResponse
import com.jamesnyakush.news.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _newsResponse = mutableStateOf<Response<NewsResponse>>(
        Response.Success(null)
    )

    val newsResponse: State<Response<NewsResponse>> = _newsResponse

    fun getTopHeadlines() = viewModelScope.launch {

        newsRepository.getTopHeadlines(
            country = Payload.country, apiKey = Payload.apiKey
        ).collect { response ->
            _newsResponse.value = response
        }

    }

    fun upsert(article: List<Article>) = viewModelScope.launch {
        newsRepository.upsertNews(article)
    }


    fun getArticles(): Flow<List<Article>>{
       return  newsRepository.getSavedArticle()
    }
}