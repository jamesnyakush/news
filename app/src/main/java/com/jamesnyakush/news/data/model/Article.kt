package com.jamesnyakush.news.data.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
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
    val urlToImage: String? = ""
)