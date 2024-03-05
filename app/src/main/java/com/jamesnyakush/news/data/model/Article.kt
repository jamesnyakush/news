package com.jamesnyakush.news.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.Nullable

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
//    val source: Source,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)