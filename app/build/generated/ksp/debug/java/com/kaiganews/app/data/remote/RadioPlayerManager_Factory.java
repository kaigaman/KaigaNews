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
public final class RadioPlayerManager_Factory implements Factory<RadioPlayerManager> {
  @Override
  public RadioPlayerManager get() {
    return newInstance();
  }

  public static RadioPlayerManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RadioPlayerManager newInstance() {
    return new RadioPlayerManager();
  }

  private static final class InstanceHolder {
    private static final RadioPlayerManager_Factory INSTANCE = new RadioPlayerManager_Factory();
  }
}
