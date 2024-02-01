package com.example.tiptime.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News_table")
data class ArticleR(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val sourceId: String?,
    val sourceName: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)
