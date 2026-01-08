package com.skure.app.food;

import com.skure.app.repository.FoodAdviceRepository;
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
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class FoodViewModel_Factory implements Factory<FoodViewModel> {
  private final Provider<FoodAdviceRepository> repoProvider;

  public FoodViewModel_Factory(Provider<FoodAdviceRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public FoodViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static FoodViewModel_Factory create(Provider<FoodAdviceRepository> repoProvider) {
    return new FoodViewModel_Factory(repoProvider);
  }

  public static FoodViewModel newInstance(FoodAdviceRepository repo) {
    return new FoodViewModel(repo);
  }
}
