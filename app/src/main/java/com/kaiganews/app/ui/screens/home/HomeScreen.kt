package com.kaiganews.app.ui.screens.home

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaiganews.app.domain.model.Article
import com.kaiganews.app.ui.components.ArticleCard
import com.kaiganews.app.ui.components.EmptyState
import com.kaiganews.app.ui.components.ErrorMessage
import com.kaiganews.app.ui.components.FeaturedArticleCard
import com.kaiganews.app.ui.components.LoadingIndicator
import com.kaiganews.app.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onArticleClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Kaiga News",
                            fontWeight = FontWeight.Bold
                        )
                        if (uiState.lastUpdated != null) {
                            Text(
                                text = "Updated: ${uiState.lastUpdated}",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }
                },
                actions = {
                    if (uiState.isRefreshing) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 12.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    }
                    IconButton(
                        onClick = { viewModel.refreshArticles() },
                        enabled = !uiState.isRefreshing
                    ) {
                        Icon(
                            imageVector = if (uiState.isRefreshing) Icons.Default.Sync else Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Refresh indicator banner
            if (uiState.isRefreshing && uiState.articles.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Refreshing...",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            
            Box(modifier = Modifier.weight(1f)) {
                when {
                    uiState.isLoading && uiState.articles.isEmpty() -> {
                        LoadingContent()
                    }
                    uiState.error != null && uiState.articles.isEmpty() -> {
                        ErrorContent(
                            message = uiState.error ?: "Unknown error",
                            onRetry = { viewModel.refreshArticles() }
                        )
                    }
                    uiState.articles.isEmpty() -> {
                        EmptyContent(onRefresh = { viewModel.refreshArticles() })
                    }
                    else -> {
                        ArticleList(
                            articles = uiState.articles,
                            onArticleClick = onArticleClick,
                            onShareClick = { article ->
                                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_SUBJECT, article.title)
                                    putExtra(Intent.EXTRA_TEXT, "${article.title}\n\n${article.link}")
                                }
                                context.startActivity(Intent.createChooser(shareIntent, "Share article"))
                            },
                            onBookmarkClick = { article ->
                                viewModel.bookmarkArticle(article.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadingIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Loading latest news...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ErrorMessage(
            message = message,
            onRetry = onRetry
        )
    }
}

@Composable
private fun EmptyContent(onRefresh: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmptyState(message = "No articles found")
            Spacer(modifier = Modifier.height(16.dp))
            androidx.compose.material3.TextButton(onClick = onRefresh) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Tap to refresh")
            }
        }
    }
}

@Composable
private fun ArticleList(
    articles: List<Article>,
    onArticleClick: (String) -> Unit,
    onShareClick: (Article) -> Unit,
    onBookmarkClick: (Article) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (articles.isNotEmpty()) {
            item {
                FeaturedArticleCard(
                    article = articles.first(),
                    onClick = { onArticleClick(articles.first().id) }
                )
            }
            
            item {
                Text(
                    text = "Latest News",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            items(
                items = articles.drop(1),
                key = { it.id }
            ) { article ->
                ArticleCard(
                    article = article,
                    onClick = { onArticleClick(article.id) },
                    onShareClick = { onShareClick(article) },
                    onBookmarkClick = { onBookmarkClick(article) }
                )
            }
        }
        
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
