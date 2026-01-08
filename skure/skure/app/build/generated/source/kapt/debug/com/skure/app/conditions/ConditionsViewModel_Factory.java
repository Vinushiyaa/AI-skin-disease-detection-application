package com.skure.app.conditions;

import com.skure.app.database.SkinDatabaseRepository;
import com.skure.app.repository.AuthRepository;
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
public final class ConditionsViewModel_Factory implements Factory<ConditionsViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<SkinDatabaseRepository> databaseRepositoryProvider;

  public ConditionsViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<SkinDatabaseRepository> databaseRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.databaseRepositoryProvider = databaseRepositoryProvider;
  }

  @Override
  public ConditionsViewModel get() {
    return newInstance(authRepositoryProvider.get(), databaseRepositoryProvider.get());
  }

  public static ConditionsViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<SkinDatabaseRepository> databaseRepositoryProvider) {
    return new ConditionsViewModel_Factory(authRepositoryProvider, databaseRepositoryProvider);
  }

  public static ConditionsViewModel newInstance(AuthRepository authRepository,
      SkinDatabaseRepository databaseRepository) {
    return new ConditionsViewModel(authRepository, databaseRepository);
  }
}
