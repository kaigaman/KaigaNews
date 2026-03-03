package com.kaiganews.app.ui.viewmodel;

import com.kaiganews.app.domain.usecase.GetArticlesByCategoryUseCase;
import com.kaiganews.app.domain.usecase.GetCategoriesUseCase;
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
public final class CategoryViewModel_Factory implements Factory<CategoryViewModel> {
  private final Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider;

  private final Provider<GetArticlesByCategoryUseCase> getArticlesByCategoryUseCaseProvider;

  public CategoryViewModel_Factory(Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<GetArticlesByCategoryUseCase> getArticlesByCategoryUseCaseProvider) {
    this.getCategoriesUseCaseProvider = getCategoriesUseCaseProvider;
    this.getArticlesByCategoryUseCaseProvider = getArticlesByCategoryUseCaseProvider;
  }

  @Override
  public CategoryViewModel get() {
    return newInstance(getCategoriesUseCaseProvider.get(), getArticlesByCategoryUseCaseProvider.get());
  }

  public static CategoryViewModel_Factory create(
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<GetArticlesByCategoryUseCase> getArticlesByCategoryUseCaseProvider) {
    return new CategoryViewModel_Factory(getCategoriesUseCaseProvider, getArticlesByCategoryUseCaseProvider);
  }

  public static CategoryViewModel newInstance(GetCategoriesUseCase getCategoriesUseCase,
      GetArticlesByCategoryUseCase getArticlesByCategoryUseCase) {
    return new CategoryViewModel(getCategoriesUseCase, getArticlesByCategoryUseCase);
  }
}
