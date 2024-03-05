package com.jamesnyakush.news.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.jamesnyakush.news.data.model.Article


@Dao
interface NewsDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertNews(article:List<Article>)
}