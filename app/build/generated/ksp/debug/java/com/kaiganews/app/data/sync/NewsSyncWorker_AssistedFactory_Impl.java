package com.kaiganews.app.data.sync;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class NewsSyncWorker_AssistedFactory_Impl implements NewsSyncWorker_AssistedFactory {
  private final NewsSyncWorker_Factory delegateFactory;

  NewsSyncWorker_AssistedFactory_Impl(NewsSyncWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public NewsSyncWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<NewsSyncWorker_AssistedFactory> create(
      NewsSyncWorker_Factory delegateFactory) {
    return InstanceFactory.create(new NewsSyncWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<NewsSyncWorker_AssistedFactory> createFactoryProvider(
      NewsSyncWorker_Factory delegateFactory) {
    return InstanceFactory.create(new NewsSyncWorker_AssistedFactory_Impl(delegateFactory));
  }
}
