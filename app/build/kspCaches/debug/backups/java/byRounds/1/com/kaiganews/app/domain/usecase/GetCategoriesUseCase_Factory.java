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
public final class GetCategoriesUseCase_Factory implements Factory<GetCategoriesUseCase> {
  private final Provider<NewsRepository> repositoryProvider;

  public GetCategoriesUseCase_Factory(Provider<NewsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetCategoriesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetCategoriesUseCase_Factory create(Provider<NewsRepository> repositoryProvider) {
    return new GetCategoriesUseCase_Factory(repositoryProvider);
  }

  public static GetCategoriesUseCase newInstance(NewsRepository repository) {
    return new GetCategoriesUseCase(repository);
  }
}
