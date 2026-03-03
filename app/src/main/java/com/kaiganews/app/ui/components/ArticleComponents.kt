package com.kaiganews.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kaiganews.app.domain.model.Article
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            article.imageUrl?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = article.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                if (article.categories.isNotEmpty()) {
                    Text(
                        text = article.categories.first().uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
                
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = article.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatDate(article.publishedAt),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    
                    Row {
                        IconButton(
                            onClick = onShareClick,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share",
                                modifier = Modifier.size(18.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        
                        IconButton(
                            onClick = onBookmarkClick,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = if (article.isBookmarked) 
                                    Icons.Filled.Bookmark 
                                else 
                                    Icons.Outlined.BookmarkBorder,
                                contentDescription = "Bookmark",
                                modifier = Modifier.size(18.dp),
                                tint = if (article.isBookmarked) 
                                    MaterialTheme.colorScheme.primary 
                                else 
                                    MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ArticleCardHorizontal(
    article: Article,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            article.imageUrl?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = article.title,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                if (article.categories.isNotEmpty()) {
                    Text(
                        text = article.categories.first().uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = formatDate(article.publishedAt),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun FeaturedArticleCard(
    article: Article,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            article.imageUrl?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = article.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    contentScale = ContentScale.Crop
                )
            }
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(
                        Color.Black.copy(alpha = 0.5f)
                    )
            )
            
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                if (article.categories.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = article.categories.first().uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = article.author,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    Text(
                        text = " • ",
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    Text(
                        text = formatDate(article.publishedAt),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material3.CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ErrorMessage(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(8.dp))
        androidx.compose.material3.TextButton(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Composable
fun EmptyState(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun formatDate(date: java.util.Date): String {
    val now = System.currentTimeMillis()
    val diff = now - date.time
    
    return when {
        diff < 60 * 60 * 1000 -> {
            val minutes = diff / (60 * 1000)
            "${minutes}m ago"
        }
        diff < 24 * 60 * 60 * 1000 -> {
            val hours = diff / (60 * 60 * 1000)
            "${hours}h ago"
        }
        diff < 7 * 24 * 60 * 60 * 1000 -> {
            val days = diff / (24 * 60 * 60 * 1000)
            "${days}d ago"
        }
        else -> {
            SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(date)
        }
    }
}
