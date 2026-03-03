package com.kaiganews.app.ui.viewmodel;

import com.kaiganews.app.domain.usecase.SearchArticlesUseCase;
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
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<SearchArticlesUseCase> searchArticlesUseCaseProvider;

  public SearchViewModel_Factory(Provider<SearchArticlesUseCase> searchArticlesUseCaseProvider) {
    this.searchArticlesUseCaseProvider = searchArticlesUseCaseProvider;
  }

  @Override
  public SearchViewModel get() {
    return newInstance(searchArticlesUseCaseProvider.get());
  }

  public static SearchViewModel_Factory create(
      Provider<SearchArticlesUseCase> searchArticlesUseCaseProvider) {
    return new SearchViewModel_Factory(searchArticlesUseCaseProvider);
  }

  public static SearchViewModel newInstance(SearchArticlesUseCase searchArticlesUseCase) {
    return new SearchViewModel(searchArticlesUseCase);
  }
}
