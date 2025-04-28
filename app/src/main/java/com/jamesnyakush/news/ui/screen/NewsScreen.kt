package com.jamesnyakush.news.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.jamesnyakush.news.data.Response
import com.jamesnyakush.news.data.model.Article
import com.jamesnyakush.news.ui.component.NewsCard
import com.jamesnyakush.news.ui.nav.Screen
import com.jamesnyakush.news.ui.nav.Screen.NewsList
import com.jamesnyakush.news.ui.viewmodel.NewsViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    navController: NavController,
    viewModel: NewsViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getTopHeadlines()
    }


    fun navigateToDetail(article: Article) {
        navController.currentBackStackEntry?.savedStateHandle?.apply {
            set("article", article)
        }

        navController.navigate(Screen.NewsDetail.route)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "News") },
                actions = {
                    Text(
                        text = "Saved",
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
//                                navController.navigate(NewsList.Saved.route)
                            }
                    )
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {

                when (val newsResponse = viewModel.newsResponse.value) {
                    is Response.Loading -> {
                        Text(text = "Loading")
                    }

                    is Response.Success -> {
                        LazyColumn {
                            newsResponse.data?.articles?.let { articles ->
                                items(articles) { article ->

                                    Box(
                                        modifier = Modifier.clickable(onClick = { navigateToDetail(article) })
                                    ) {
                                        NewsCard(
                                            article = article,
                                        )
                                    }
                                }
                                viewModel.upsert(articles)
                            }
                        }
                    }

                    is Response.Failure -> {}
                }

            }
        }
    )
}