package com.jamesnyakush.news.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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


    val all = vm.getArticles().collectAsState(initial = emptyList())

    if (all.value.isEmpty()) {
        when (val newsResponse = vm.newsResponse.value) {
            is Response.Loading -> {
                Text(text = "Loading")
            }

            is Response.Success -> {
                LazyColumn {
                    newsResponse.data?.articles?.let { articles ->
                        items(articles) { article ->

                            if (article.title != "[Removed]") {
                                NewsCard(
                                    title = article.title,
                                    description = article.description,
                                    urlToImage = article.urlToImage,
                                    publishedAt = article.publishedAt
                                )
                            }

                        }
                        vm.upsert(articles)
                    }

                }
            }

            is Response.Failure -> {}
        }
    } else {
        LazyColumn {
            items(all.value) { article ->
                if (article.title != "[Removed]") {
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

}






