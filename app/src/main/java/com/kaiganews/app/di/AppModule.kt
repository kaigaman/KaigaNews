package com.kaiganews.app.di

import android.content.Context
import androidx.room.Room
import com.kaiganews.app.data.local.KaigaNewsDatabase
import com.kaiganews.app.data.local.dao.ArticleDao
import com.kaiganews.app.data.local.dao.CategoryDao
import com.kaiganews.app.data.repository.NewsRepositoryImpl
import com.kaiganews.app.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): KaigaNewsDatabase {
        return Room.databaseBuilder(
            context,
            KaigaNewsDatabase::class.java,
            "kaiga_news_db"
        ).build()
    }
    
    @Provides
    @Singleton
    fun provideArticleDao(database: KaigaNewsDatabase): ArticleDao {
        return database.articleDao()
    }
    
    @Provides
    @Singleton
    fun provideCategoryDao(database: KaigaNewsDatabase): CategoryDao {
        return database.categoryDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}
