package com.skure.app.ui;

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
public final class DatabaseDebugViewModel_Factory implements Factory<DatabaseDebugViewModel> {
  private final Provider<SkinDatabaseRepository> repositoryProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  public DatabaseDebugViewModel_Factory(Provider<SkinDatabaseRepository> repositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    this.repositoryProvider = repositoryProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public DatabaseDebugViewModel get() {
    return newInstance(repositoryProvider.get(), authRepositoryProvider.get());
  }

  public static DatabaseDebugViewModel_Factory create(
      Provider<SkinDatabaseRepository> repositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    return new DatabaseDebugViewModel_Factory(repositoryProvider, authRepositoryProvider);
  }

  public static DatabaseDebugViewModel newInstance(SkinDatabaseRepository repository,
      AuthRepository authRepository) {
    return new DatabaseDebugViewModel(repository, authRepository);
  }
}
