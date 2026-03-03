package com.kaiganews.app.domain.usecase

import com.kaiganews.app.domain.model.Article
import com.kaiganews.app.domain.model.Category
import com.kaiganews.app.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> = repository.getLatestArticles()
}

class GetArticlesByCategoryUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(category: String): Flow<List<Article>> = 
        repository.getArticlesByCategory(category)
}

class GetArticleByIdUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(id: String): Flow<Article?> = repository.getArticleById(id)
}

class SearchArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(query: String): Flow<List<Article>> = repository.searchArticles(query)
}

class GetCategoriesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<Category>> = repository.getCategories()
}

class RefreshArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): Result<Unit> = repository.refreshArticles()
}

class BookmarkArticleUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(articleId: String) = repository.bookmarkArticle(articleId)
}

class GetBookmarkedArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> = repository.getBookmarkedArticles()
}
