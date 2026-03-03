package com.kaiganews.app.data.remote

import com.prof18.rssparser.RssParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RssService @Inject constructor() {
    
    private val rssParser = RssParser()
    
    companion object {
        const val RSS_URL = "https://kaiga.online/feed/"
        private val dateFormats = listOf(
            SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH),
            SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH),
            SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        )
    }
    
    suspend fun fetchRssFeed(): Result<List<RssArticle>> = withContext(Dispatchers.IO) {
        try {
            val channel = rssParser.getRssChannel(RSS_URL)
            
            val articles = channel.items.mapNotNull { item ->
                try {
                    val title = item.title ?: return@mapNotNull null
                    val link = item.link ?: return@mapNotNull null
                    
                    val pubDate = item.pubDate?.let { parseDate(it) } ?: System.currentTimeMillis()
                    
                    val imageUrl = item.image ?: 
                        extractImageFromContent(item.content ?: item.description ?: "")
                    
                    val content = item.content ?: item.description ?: ""
                    
                    RssArticle(
                        id = link.hashCode().toString(),
                        title = title,
                        description = item.description ?: "",
                        content = content,
                        imageUrl = imageUrl,
                        link = link,
                        author = item.author ?: "Kaiga News",
                        publishedAt = pubDate,
                        categories = item.categories ?: emptyList()
                    )
                } catch (e: Exception) {
                    null
                }
            }
            
            Result.success(articles)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private fun parseDate(dateString: String): Long {
        for (format in dateFormats) {
            try {
                return format.parse(dateString)?.time ?: continue
            } catch (e: Exception) {
                continue
            }
        }
        return System.currentTimeMillis()
    }
    
    private fun extractImageFromContent(content: String): String? {
        val imgRegex = """<img[^>]+src\s*=\s*["']([^"']+)["']""".toRegex()
        return imgRegex.find(content)?.groupValues?.getOrNull(1)
    }
}

data class RssArticle(
    val id: String,
    val title: String,
    val description: String,
    val content: String,
    val imageUrl: String?,
    val link: String,
    val author: String,
    val publishedAt: Long,
    val categories: List<String>
)
