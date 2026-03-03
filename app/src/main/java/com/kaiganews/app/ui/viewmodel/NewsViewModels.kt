package com.kaiganews.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaiganews.app.domain.model.Article
import com.kaiganews.app.domain.model.Category
import com.kaiganews.app.domain.usecase.BookmarkArticleUseCase
import com.kaiganews.app.domain.usecase.GetArticlesByCategoryUseCase
import com.kaiganews.app.domain.usecase.GetCategoriesUseCase
import com.kaiganews.app.domain.usecase.GetLatestArticlesUseCase
import com.kaiganews.app.domain.usecase.RefreshArticlesUseCase
import com.kaiganews.app.domain.usecase.SearchArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestArticlesUseCase: GetLatestArticlesUseCase,
    private val refreshArticlesUseCase: RefreshArticlesUseCase,
    private val bookmarkArticleUseCase: BookmarkArticleUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        loadArticles()
        refreshArticles()
    }
    
    private fun loadArticles() {
        viewModelScope.launch {
            getLatestArticlesUseCase()
                .catch { e ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.message ?: "Unknown error"
                    )
                }
                .collect { articles ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        articles = articles,
                        error = null,
                        lastUpdated = if (articles.isNotEmpty()) formatLastUpdatedTime() else null
                    )
                }
        }
    }
    
    fun refreshArticles() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isRefreshing = true)
            val result = refreshArticlesUseCase()
            _uiState.value = _uiState.value.copy(
                isRefreshing = false,
                lastUpdated = formatLastUpdatedTime(),
                error = result.exceptionOrNull()?.message
            )
        }
    }
    
    private fun formatLastUpdatedTime(): String {
        val now = java.util.Date()
        val format = java.text.SimpleDateFormat("hh:mm a", java.util.Locale.getDefault())
        return format.format(now)
    }
    
    fun bookmarkArticle(articleId: String) {
        viewModelScope.launch {
            bookmarkArticleUseCase(articleId)
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null,
    val lastUpdated: String? = null
)

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getArticlesByCategoryUseCase: GetArticlesByCategoryUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()
    
    init {
        loadCategories()
    }
    
    private fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase()
                .catch { e ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
                .collect { categories ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        categories = categories
                    )
                }
        }
    }
    
    fun loadArticlesByCategory(category: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoadingArticles = true)
            getArticlesByCategoryUseCase(category)
                .catch { e ->
                    _uiState.value = _uiState.value.copy(
                        isLoadingArticles = false,
                        error = e.message
                    )
                }
                .collect { articles ->
                    _uiState.value = _uiState.value.copy(
                        isLoadingArticles = false,
                        categoryArticles = articles
                    )
                }
        }
    }
}

data class CategoryUiState(
    val isLoading: Boolean = true,
    val isLoadingArticles: Boolean = false,
    val categories: List<Category> = emptyList(),
    val categoryArticles: List<Article> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchArticlesUseCase: SearchArticlesUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()
    
    fun search(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
        
        if (query.isBlank()) {
            _uiState.value = _uiState.value.copy(
                articles = emptyList(),
                isSearching = false
            )
            return
        }
        
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isSearching = true)
            searchArticlesUseCase(query)
                .catch { e ->
                    _uiState.value = _uiState.value.copy(
                        isSearching = false,
                        error = e.message
                    )
                }
                .collect { articles ->
                    _uiState.value = _uiState.value.copy(
                        isSearching = false,
                        articles = articles
                    )
                }
        }
    }
}

data class SearchUiState(
    val query: String = "",
    val isSearching: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val getArticleByIdUseCase: com.kaiganews.app.domain.usecase.GetArticleByIdUseCase,
    private val bookmarkArticleUseCase: BookmarkArticleUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ArticleDetailUiState())
    val uiState: StateFlow<ArticleDetailUiState> = _uiState.asStateFlow()
    
    fun loadArticle(articleId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getArticleByIdUseCase(articleId)
                .catch { e ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
                .collect { article ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        article = article,
                        error = if (article == null) "Article not found" else null
                    )
                }
        }
    }
    
    fun toggleBookmark() {
        val article = _uiState.value.article ?: return
        viewModelScope.launch {
            bookmarkArticleUseCase(article.id)
        }
    }
}

data class ArticleDetailUiState(
    val isLoading: Boolean = true,
    val article: Article? = null,
    val error: String? = null
)
