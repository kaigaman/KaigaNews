package com.kaiganews.app.ui.viewmodel;

import com.kaiganews.app.domain.usecase.BookmarkArticleUseCase;
import com.kaiganews.app.domain.usecase.GetArticleByIdUseCase;
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
public final class ArticleDetailViewModel_Factory implements Factory<ArticleDetailViewModel> {
  private final Provider<GetArticleByIdUseCase> getArticleByIdUseCaseProvider;

  private final Provider<BookmarkArticleUseCase> bookmarkArticleUseCaseProvider;

  public ArticleDetailViewModel_Factory(
      Provider<GetArticleByIdUseCase> getArticleByIdUseCaseProvider,
      Provider<BookmarkArticleUseCase> bookmarkArticleUseCaseProvider) {
    this.getArticleByIdUseCaseProvider = getArticleByIdUseCaseProvider;
    this.bookmarkArticleUseCaseProvider = bookmarkArticleUseCaseProvider;
  }

  @Override
  public ArticleDetailViewModel get() {
    return newInstance(getArticleByIdUseCaseProvider.get(), bookmarkArticleUseCaseProvider.get());
  }

  public static ArticleDetailViewModel_Factory create(
      Provider<GetArticleByIdUseCase> getArticleByIdUseCaseProvider,
      Provider<BookmarkArticleUseCase> bookmarkArticleUseCaseProvider) {
    return new ArticleDetailViewModel_Factory(getArticleByIdUseCaseProvider, bookmarkArticleUseCaseProvider);
  }

  public static ArticleDetailViewModel newInstance(GetArticleByIdUseCase getArticleByIdUseCase,
      BookmarkArticleUseCase bookmarkArticleUseCase) {
    return new ArticleDetailViewModel(getArticleByIdUseCase, bookmarkArticleUseCase);
  }
}
