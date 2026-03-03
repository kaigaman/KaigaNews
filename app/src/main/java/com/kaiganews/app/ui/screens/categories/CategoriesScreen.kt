package com.kaiganews.app.ui.screens.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaiganews.app.domain.model.Article
import com.kaiganews.app.domain.model.Category
import com.kaiganews.app.ui.components.ArticleCardHorizontal
import com.kaiganews.app.ui.components.EmptyState
import com.kaiganews.app.ui.components.LoadingIndicator
import com.kaiganews.app.ui.viewmodel.CategoryUiState
import com.kaiganews.app.ui.viewmodel.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    onCategoryClick: (String) -> Unit,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = selectedCategory ?: "Categories",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    if (selectedCategory != null) {
                        IconButton(onClick = { selectedCategory = null }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        if (selectedCategory != null) {
            CategoryArticlesScreen(
                categoryName = selectedCategory!!,
                uiState = uiState,
                onArticleClick = { /* Navigate to article */ },
                modifier = Modifier.padding(paddingValues)
            )
        } else {
            CategoriesList(
                categories = uiState.categories,
                onCategoryClick = { category ->
                    selectedCategory = category.name
                    viewModel.loadArticlesByCategory(category.name)
                },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun CategoriesList(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    if (categories.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LoadingIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                CategoryCard(
                    category = category,
                    onClick = { onCategoryClick(category) }
                )
            }
        }
    }
}

@Composable
private fun CategoryCard(
    category: Category,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${category.articleCount} articles",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
private fun CategoryArticlesScreen(
    categoryName: String,
    uiState: CategoryUiState,
    onArticleClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (uiState.isLoadingArticles) {
            item {
                LoadingIndicator()
            }
        } else if (uiState.categoryArticles.isEmpty()) {
            item {
                EmptyState(message = "No articles in this category")
            }
        } else {
            items(uiState.categoryArticles) { article ->
                ArticleCardHorizontal(
                    article = article,
                    onClick = { onArticleClick(article.id) }
                )
            }
        }
        
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
