package com.kaiganews.app.domain.repository

import com.kaiganews.app.domain.model.Article
import com.kaiganews.app.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getLatestArticles(): Flow<List<Article>>
    fun getArticlesByCategory(category: String): Flow<List<Article>>
    fun getArticleById(id: String): Flow<Article?>
    fun searchArticles(query: String): Flow<List<Article>>
    fun getCategories(): Flow<List<Category>>
    suspend fun refreshArticles(): Result<Unit>
    suspend fun bookmarkArticle(articleId: String)
    suspend fun removeBookmark(articleId: String)
    fun getBookmarkedArticles(): Flow<List<Article>>
}
