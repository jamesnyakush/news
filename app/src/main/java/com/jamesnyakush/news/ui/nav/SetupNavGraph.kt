package com.jamesnyakush.news.ui.nav

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.jamesnyakush.news.data.model.Article
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
            route = Screen.NewsDetail.route,
            arguments = listOf(
                navArgument("article") {
                    //type = ProfileArgType()
                }
            )
        ) { backStackEntry ->

//            backStackEntry.arguments?.getString("article")?.let {
//                val article =  Gson().fromJson(it, Article::class.java)
//                NewsDetailScreen(article = article!!)
//            }


        }

//        composable(
//            route = Screen.NewsDetail.route
//        ) {
//
//            val rsl =
//                navHostController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
//            NewsDetailScreen(article = article!!)
//        }
    }
}
