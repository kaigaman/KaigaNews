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
public final class RadioStreamService_Factory implements Factory<RadioStreamService> {
  @Override
  public RadioStreamService get() {
    return newInstance();
  }

  public static RadioStreamService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RadioStreamService newInstance() {
    return new RadioStreamService();
  }

  private static final class InstanceHolder {
    private static final RadioStreamService_Factory INSTANCE = new RadioStreamService_Factory();
  }
}
