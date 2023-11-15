package com.example.tiptime.data

data class ArticlesList(
    val articles: List<ArticleX>,
    val status: String,
    val totalResults: Int
)