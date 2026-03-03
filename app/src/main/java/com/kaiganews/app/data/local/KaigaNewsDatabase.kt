package com.kaiganews.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaiganews.app.data.local.dao.ArticleDao
import com.kaiganews.app.data.local.dao.CategoryDao
import com.kaiganews.app.data.local.entity.ArticleEntity
import com.kaiganews.app.data.local.entity.CategoryEntity

@Database(
    entities = [ArticleEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class KaigaNewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun categoryDao(): CategoryDao
}
