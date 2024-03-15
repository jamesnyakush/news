package com.jamesnyakush.news.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jamesnyakush.news.ui.screen.NewsDetailScreen
import com.jamesnyakush.news.ui.screen.NewsScreen

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.NewsList.route
    ) {

        composable(
            route = Screen.NewsList.route
        ) {
            NewsScreen(
                navHostController
            )
        }

        composable(
            route = Screen.NewsDetail.route
        ) {
            NewsDetailScreen()
        }
    }
}