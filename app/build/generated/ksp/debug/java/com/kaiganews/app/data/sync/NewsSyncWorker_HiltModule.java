package com.kaiganews.app.data.sync;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.annotation.processing.Generated;

@Generated("androidx.hilt.AndroidXHiltProcessor")
@Module
@InstallIn(SingletonComponent.class)
@OriginatingElement(
    topLevelClass = NewsSyncWorker.class
)
public interface NewsSyncWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.kaiganews.app.data.sync.NewsSyncWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(NewsSyncWorker_AssistedFactory factory);
}
