package com.kaiganews.app.data.remote;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class RssService_Factory implements Factory<RssService> {
  @Override
  public RssService get() {
    return newInstance();
  }

  public static RssService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RssService newInstance() {
    return new RssService();
  }

  private static final class InstanceHolder {
    private static final RssService_Factory INSTANCE = new RssService_Factory();
  }
}
