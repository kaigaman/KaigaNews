package com.kaiganews.app.ui.viewmodel;

import com.kaiganews.app.data.remote.RadioPlayerManager;
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
public final class RadioViewModel_Factory implements Factory<RadioViewModel> {
  private final Provider<RadioPlayerManager> radioPlayerManagerProvider;

  public RadioViewModel_Factory(Provider<RadioPlayerManager> radioPlayerManagerProvider) {
    this.radioPlayerManagerProvider = radioPlayerManagerProvider;
  }

  @Override
  public RadioViewModel get() {
    return newInstance(radioPlayerManagerProvider.get());
  }

  public static RadioViewModel_Factory create(
      Provider<RadioPlayerManager> radioPlayerManagerProvider) {
    return new RadioViewModel_Factory(radioPlayerManagerProvider);
  }

  public static RadioViewModel newInstance(RadioPlayerManager radioPlayerManager) {
    return new RadioViewModel(radioPlayerManager);
  }
}
