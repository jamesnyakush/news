package com.jamesnyakush.news.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jamesnyakush.news.data.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNews(article: List<Article>)

    @Query("select * from article Order by publishedAt DESC")
    fun getSavedArticle(): Flow<List<Article>>

}