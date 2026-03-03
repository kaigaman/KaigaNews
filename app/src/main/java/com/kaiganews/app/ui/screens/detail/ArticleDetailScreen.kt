package com.kaiganews.app.ui.screens.detail

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kaiganews.app.ui.components.ErrorMessage
import com.kaiganews.app.ui.components.LoadingIndicator
import com.kaiganews.app.ui.viewmodel.ArticleDetailViewModel
import org.jsoup.Jsoup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    articleId: String,
    onBackClick: () -> Unit,
    viewModel: ArticleDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    
    LaunchedEffect(articleId) {
        viewModel.loadArticle(articleId)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    uiState.article?.let { article ->
                        IconButton(
                            onClick = {
                                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_SUBJECT, article.title)
                                    putExtra(Intent.EXTRA_TEXT, "${article.title}\n\n${article.link}")
                                }
                                context.startActivity(Intent.createChooser(shareIntent, "Share article"))
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share",
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = { viewModel.toggleBookmark() }) {
                            Icon(
                                imageVector = if (article.isBookmarked)
                                    Icons.Filled.Bookmark
                                else
                                    Icons.Outlined.BookmarkBorder,
                                contentDescription = "Bookmark",
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingIndicator()
                }
            }
            uiState.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    ErrorMessage(
                        message = uiState.error ?: "Unknown error",
                        onRetry = { viewModel.loadArticle(articleId) }
                    )
                }
            }
            uiState.article != null -> {
                val article = uiState.article!!
                
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        article.imageUrl?.let { imageUrl ->
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = article.title,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                        
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Color.Black.copy(alpha = 0.4f)
                                )
                        )
                        
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        ) {
                            if (article.categories.isNotEmpty()) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    article.categories.take(2).forEach { category ->
                                        Box(
                                            modifier = Modifier
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    RoundedCornerShape(4.dp)
                                                )
                                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                        ) {
                                            Text(
                                                text = category.uppercase(),
                                                style = MaterialTheme.typography.labelSmall,
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                            
                            Text(
                                text = article.title,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                    
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "By ${article.author}",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = formatFullDate(article.publishedAt),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        val cleanContent = cleanHtmlContent(article.content)
                        Text(
                            text = cleanContent.ifEmpty { article.description },
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.5
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Text(
                            text = "Source: ${article.link}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }
            }
        }
    }
}

private fun cleanHtmlContent(html: String): String {
    return try {
        val doc = Jsoup.parse(html)
        doc.select("script, style, nav, header, footer, aside").remove()
        doc.body().text()
    } catch (e: Exception) {
        html.replace(Regex("<[^>]*>"), "")
    }
}

private fun formatFullDate(date: java.util.Date): String {
    val formatter = java.text.SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm a", java.util.Locale.getDefault())
    return formatter.format(date)
}
