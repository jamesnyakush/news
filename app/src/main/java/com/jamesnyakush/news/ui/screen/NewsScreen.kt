package com.jamesnyakush.news.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jamesnyakush.news.data.Response
import com.jamesnyakush.news.ui.component.NewsCard
import com.jamesnyakush.news.ui.nav.Screen
import com.jamesnyakush.news.ui.viewmodel.NewsViewModel
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun NewsScreen(
    navController: NavController
) {
    val vm = koinViewModel<NewsViewModel>()

    LaunchedEffect(Unit) {
        vm.getTopHeadlines("us", "75cdd7daba1e4339b7cbccfe40a620b6")
    }


    val all = vm.getArticles().collectAsState(initial = emptyList())

    if (all.value.isNotEmpty()) {

        LazyColumn {
            items(all.value) { article ->
                if (article.title != "[Removed]") {
                    NewsCard(title = article.title,
                        description = article.description,
                        urlToImage = article.urlToImage,
                        publishedAt = article.publishedAt,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.NewsDetail.route)
                            Timber.d( "Pressed Box")
                        })
                }
            }
        }


    } else {
        when (val newsResponse = vm.newsResponse.value) {
            is Response.Loading -> {
                Text(text = "Loading")
            }

            is Response.Success -> {
                LazyColumn {
                    newsResponse.data?.articles?.let { articles ->
                        items(articles) { article ->

                            if (article.title != "[Removed]") {
                                NewsCard(title = article.title,
                                    description = article.description,
                                    urlToImage = article.urlToImage,
                                    publishedAt = article.publishedAt,
                                    modifier = Modifier.clickable {
                                        navController.navigate(Screen.NewsDetail.route)
                                    })
                            }

                        }
                        vm.upsert(articles)
                    }

                }
            }

            is Response.Failure -> {}
        }
    }

}






