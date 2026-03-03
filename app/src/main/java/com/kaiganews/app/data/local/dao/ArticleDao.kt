package com.kaiganews.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kaiganews.app.data.local.entity.ArticleEntity
import com.kaiganews.app.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles ORDER BY publishedAt DESC")
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE categories LIKE '%' || :category || '%' ORDER BY publishedAt DESC")
    fun getArticlesByCategory(category: String): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE id = :id")
    fun getArticleById(id: String): Flow<ArticleEntity?>

    @Query("SELECT * FROM articles WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%' ORDER BY publishedAt DESC")
    fun searchArticles(query: String): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE isBookmarked = 1 ORDER BY publishedAt DESC")
    fun getBookmarkedArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleEntity)

    @Update
    suspend fun updateArticle(article: ArticleEntity)

    @Query("UPDATE articles SET isBookmarked = :isBookmarked WHERE id = :articleId")
    suspend fun updateBookmark(articleId: String, isBookmarked: Boolean)

    @Query("DELETE FROM articles WHERE cachedAt < :timestamp")
    suspend fun deleteOldArticles(timestamp: Long)

    @Query("SELECT COUNT(*) FROM articles")
    suspend fun getArticleCount(): Int
}

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("SELECT COUNT(*) FROM categories")
    suspend fun getCategoryCount(): Int
}
