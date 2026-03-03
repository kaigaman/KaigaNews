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
public final class SearchArticlesUseCase_Factory implements Factory<SearchArticlesUseCase> {
  private final Provider<NewsRepository> repositoryProvider;

  public SearchArticlesUseCase_Factory(Provider<NewsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SearchArticlesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SearchArticlesUseCase_Factory create(Provider<NewsRepository> repositoryProvider) {
    return new SearchArticlesUseCase_Factory(repositoryProvider);
  }

  public static SearchArticlesUseCase newInstance(NewsRepository repository) {
    return new SearchArticlesUseCase(repository);
  }
}
