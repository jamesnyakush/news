package com.jamesnyakush.news.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.jamesnyakush.news.data.Response
import com.jamesnyakush.news.ui.component.NewsCard
import com.jamesnyakush.news.ui.viewmodel.NewsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsScreen() {
    val vm = koinViewModel<NewsViewModel>()

    LaunchedEffect(Unit) {
        vm.getTopHeadlines("us", "75cdd7daba1e4339b7cbccfe40a620b6")
    }

    when (val newsResponse = vm.newsResponse.value) {
        is Response.Loading -> {
            Text(text = "Loading")
        }

        is Response.Success -> {

            LazyColumn {
                newsResponse.data?.articles?.let { articles ->
                    items(articles) {article ->
                        NewsCard(
                            title = article.title,
                            description = article.description,
                            urlToImage = article.urlToImage,
                            publishedAt = article.publishedAt
                        )
                    }
                }
            }
        }

        is Response.Failure -> {}
    }
}