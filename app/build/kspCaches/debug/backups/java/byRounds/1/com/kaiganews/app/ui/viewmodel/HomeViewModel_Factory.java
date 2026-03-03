package com.kaiganews.app.ui.viewmodel;

import com.kaiganews.app.domain.usecase.BookmarkArticleUseCase;
import com.kaiganews.app.domain.usecase.GetLatestArticlesUseCase;
import com.kaiganews.app.domain.usecase.RefreshArticlesUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetLatestArticlesUseCase> getLatestArticlesUseCaseProvider;

  private final Provider<RefreshArticlesUseCase> refreshArticlesUseCaseProvider;

  private final Provider<BookmarkArticleUseCase> bookmarkArticleUseCaseProvider;

  public HomeViewModel_Factory(Provider<GetLatestArticlesUseCase> getLatestArticlesUseCaseProvider,
      Provider<RefreshArticlesUseCase> refreshArticlesUseCaseProvider,
      Provider<BookmarkArticleUseCase> bookmarkArticleUseCaseProvider) {
    this.getLatestArticlesUseCaseProvider = getLatestArticlesUseCaseProvider;
    this.refreshArticlesUseCaseProvider = refreshArticlesUseCaseProvider;
    this.bookmarkArticleUseCaseProvider = bookmarkArticleUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getLatestArticlesUseCaseProvider.get(), refreshArticlesUseCaseProvider.get(), bookmarkArticleUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetLatestArticlesUseCase> getLatestArticlesUseCaseProvider,
      Provider<RefreshArticlesUseCase> refreshArticlesUseCaseProvider,
      Provider<BookmarkArticleUseCase> bookmarkArticleUseCaseProvider) {
    return new HomeViewModel_Factory(getLatestArticlesUseCaseProvider, refreshArticlesUseCaseProvider, bookmarkArticleUseCaseProvider);
  }

  public static HomeViewModel newInstance(GetLatestArticlesUseCase getLatestArticlesUseCase,
      RefreshArticlesUseCase refreshArticlesUseCase,
      BookmarkArticleUseCase bookmarkArticleUseCase) {
    return new HomeViewModel(getLatestArticlesUseCase, refreshArticlesUseCase, bookmarkArticleUseCase);
  }
}
