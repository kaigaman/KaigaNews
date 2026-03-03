package com.kaiganews.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val content: String,
    val imageUrl: String?,
    val link: String,
    val author: String,
    val publishedAt: Long,
    val categories: String,
    val isBookmarked: Boolean = false,
    val cachedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey
    val name: String,
    val articleCount: Int = 0
)
