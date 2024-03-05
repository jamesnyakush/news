package com.jamesnyakush.news.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jamesnyakush.news.data.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDB : RoomDatabase() {
    abstract fun newsDao(): NewsDAO
}