package com.jamesnyakush.news.ui.nav

sealed class Screen(val route: String) {
    object NewsList : Screen(route = "news_list")
    object NewsDetail : Screen(route = "news_detail")
}