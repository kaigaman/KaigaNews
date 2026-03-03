package com.kaiganews.app.data.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.kaiganews.app.domain.repository.NewsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

@HiltWorker
class NewsSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val newsRepository: NewsRepository
) : CoroutineWorker(context, workerParams) {
    
    companion object {
        const val WORK_NAME = "news_sync_worker"
        private const val SYNC_INTERVAL_MINUTES = 15L
        
        fun schedule(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            
            val workRequest = PeriodicWorkRequestBuilder<NewsSyncWorker>(
                SYNC_INTERVAL_MINUTES, TimeUnit.MINUTES
            )
                .setConstraints(constraints)
                .build()
            
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
        }
        
        fun syncNow(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            
            val workRequest = androidx.work.OneTimeWorkRequestBuilder<NewsSyncWorker>()
                .setConstraints(constraints)
                .build()
            
            WorkManager.getInstance(context).enqueue(workRequest)
        }
    }
    
    override suspend fun doWork(): Result {
        return try {
            val result = newsRepository.refreshArticles()
            if (result.isSuccess) {
                Result.success()
            } else {
                Result.retry()
            }
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
