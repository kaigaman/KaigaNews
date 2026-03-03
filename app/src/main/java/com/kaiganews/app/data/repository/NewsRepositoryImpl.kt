package com.kaiganews.app.data.repository

import com.kaiganews.app.data.local.dao.ArticleDao
import com.kaiganews.app.data.local.dao.CategoryDao
import com.kaiganews.app.data.local.entity.ArticleEntity
import com.kaiganews.app.data.local.entity.CategoryEntity
import com.kaiganews.app.data.remote.RssArticle
import com.kaiganews.app.data.remote.RssService
import com.kaiganews.app.domain.model.Article
import com.kaiganews.app.domain.model.Category
import com.kaiganews.app.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao,
    private val categoryDao: CategoryDao,
    private val rssService: RssService
) : NewsRepository {
    
    companion object {
        private val CACHE_VALIDITY = TimeUnit.HOURS.toMillis(1)
    }
    
    override fun getLatestArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles().map { entities ->
            entities.map { it.toArticle() }
        }
    }
    
    override fun getArticlesByCategory(category: String): Flow<List<Article>> {
        return articleDao.getArticlesByCategory(category).map { entities ->
            entities.map { it.toArticle() }
        }
    }
    
    override fun getArticleById(id: String): Flow<Article?> {
        return articleDao.getArticleById(id).map { it?.toArticle() }
    }
    
    override fun searchArticles(query: String): Flow<List<Article>> {
        return articleDao.searchArticles(query).map { entities ->
            entities.map { it.toArticle() }
        }
    }
    
    override fun getCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories().map { entities ->
            entities.map { Category(it.name, it.articleCount) }
        }
    }
    
    override suspend fun refreshArticles(): Result<Unit> {
        return try {
            val result = rssService.fetchRssFeed()
            result.fold(
                onSuccess = { rssArticles ->
                    val entities = rssArticles.map { it.toEntity() }
                    articleDao.insertArticles(entities)
                    
                    val allCategories = rssArticles
                        .flatMap { it.categories }
                        .distinct()
                        .map { CategoryEntity(it, 0) }
                    
                    if (categoryDao.getCategoryCount() == 0) {
                        categoryDao.insertCategories(allCategories)
                    }
                    
                    articleDao.deleteOldArticles(System.currentTimeMillis() - CACHE_VALIDITY)
                    
                    Result.success(Unit)
                },
                onFailure = { error ->
                    if (articleDao.getArticleCount() == 0) {
                        Result.failure(error)
                    } else {
                        Result.success(Unit)
                    }
                }
            )
        } catch (e: Exception) {
            if (articleDao.getArticleCount() == 0) {
                Result.failure(e)
            } else {
                Result.success(Unit)
            }
        }
    }
    
    override suspend fun bookmarkArticle(articleId: String) {
        articleDao.updateBookmark(articleId, true)
    }
    
    override suspend fun removeBookmark(articleId: String) {
        articleDao.updateBookmark(articleId, false)
    }
    
    override fun getBookmarkedArticles(): Flow<List<Article>> {
        return articleDao.getBookmarkedArticles().map { entities ->
            entities.map { it.toArticle() }
        }
    }
    
    private fun ArticleEntity.toArticle(): Article {
        return Article(
            id = id,
            title = title,
            description = description,
            content = content,
            imageUrl = imageUrl,
            link = link,
            author = author,
            publishedAt = java.util.Date(publishedAt),
            categories = categories.split(",").filter { it.isNotBlank() },
            isBookmarked = isBookmarked
        )
    }
    
    private fun RssArticle.toEntity(): ArticleEntity {
        return ArticleEntity(
            id = id,
            title = title,
            description = description,
            content = content,
            imageUrl = imageUrl,
            link = link,
            author = author,
            publishedAt = publishedAt,
            categories = categories.joinToString(","),
            isBookmarked = false,
            cachedAt = System.currentTimeMillis()
        )
    }
}
