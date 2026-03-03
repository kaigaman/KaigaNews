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
public final class GetArticlesByCategoryUseCase_Factory implements Factory<GetArticlesByCategoryUseCase> {
  private final Provider<NewsRepository> repositoryProvider;

  public GetArticlesByCategoryUseCase_Factory(Provider<NewsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetArticlesByCategoryUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetArticlesByCategoryUseCase_Factory create(
      Provider<NewsRepository> repositoryProvider) {
    return new GetArticlesByCategoryUseCase_Factory(repositoryProvider);
  }

  public static GetArticlesByCategoryUseCase newInstance(NewsRepository repository) {
    return new GetArticlesByCategoryUseCase(repository);
  }
}
