package com.kaiganews.app.data.sync;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.kaiganews.app.domain.repository.NewsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class NewsSyncWorker_Factory {
  private final Provider<NewsRepository> newsRepositoryProvider;

  public NewsSyncWorker_Factory(Provider<NewsRepository> newsRepositoryProvider) {
    this.newsRepositoryProvider = newsRepositoryProvider;
  }

  public NewsSyncWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams, newsRepositoryProvider.get());
  }

  public static NewsSyncWorker_Factory create(Provider<NewsRepository> newsRepositoryProvider) {
    return new NewsSyncWorker_Factory(newsRepositoryProvider);
  }

  public static NewsSyncWorker newInstance(Context context, WorkerParameters workerParams,
      NewsRepository newsRepository) {
    return new NewsSyncWorker(context, workerParams, newsRepository);
  }
}
