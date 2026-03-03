package com.kaiganews.app.data.repository;

import com.kaiganews.app.data.local.dao.ArticleDao;
import com.kaiganews.app.data.local.dao.CategoryDao;
import com.kaiganews.app.data.remote.RssService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class NewsRepositoryImpl_Factory implements Factory<NewsRepositoryImpl> {
  private final Provider<ArticleDao> articleDaoProvider;

  private final Provider<CategoryDao> categoryDaoProvider;

  private final Provider<RssService> rssServiceProvider;

  public NewsRepositoryImpl_Factory(Provider<ArticleDao> articleDaoProvider,
      Provider<CategoryDao> categoryDaoProvider, Provider<RssService> rssServiceProvider) {
    this.articleDaoProvider = articleDaoProvider;
    this.categoryDaoProvider = categoryDaoProvider;
    this.rssServiceProvider = rssServiceProvider;
  }

  @Override
  public NewsRepositoryImpl get() {
    return newInstance(articleDaoProvider.get(), categoryDaoProvider.get(), rssServiceProvider.get());
  }

  public static NewsRepositoryImpl_Factory create(Provider<ArticleDao> articleDaoProvider,
      Provider<CategoryDao> categoryDaoProvider, Provider<RssService> rssServiceProvider) {
    return new NewsRepositoryImpl_Factory(articleDaoProvider, categoryDaoProvider, rssServiceProvider);
  }

  public static NewsRepositoryImpl newInstance(ArticleDao articleDao, CategoryDao categoryDao,
      RssService rssService) {
    return new NewsRepositoryImpl(articleDao, categoryDao, rssService);
  }
}
