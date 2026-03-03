package com.kaiganews.app.domain.model

import java.util.Date

data class Article(
    val id: String,
    val title: String,
    val description: String,
    val content: String,
    val imageUrl: String?,
    val link: String,
    val author: String,
    val publishedAt: Date,
    val categories: List<String>,
    val isBookmarked: Boolean = false
)

data class Category(
    val name: String,
    val articleCount: Int = 0
)

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String, val data: T? = null) : Resource<T>()
}
