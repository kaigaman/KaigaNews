package com.kaiganews.app.domain.usecase;

import com.kaiganews.app.domain.repository.NewsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class RefreshArticlesUseCase_Factory implements Factory<RefreshArticlesUseCase> {
  private final Provider<NewsRepository> repositoryProvider;

  public RefreshArticlesUseCase_Factory(Provider<NewsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public RefreshArticlesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static RefreshArticlesUseCase_Factory create(Provider<NewsRepository> repositoryProvider) {
    return new RefreshArticlesUseCase_Factory(repositoryProvider);
  }

  public static RefreshArticlesUseCase newInstance(NewsRepository repository) {
    return new RefreshArticlesUseCase(repository);
  }
}
