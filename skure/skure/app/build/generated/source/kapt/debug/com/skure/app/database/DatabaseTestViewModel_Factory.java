package com.skure.app.database;

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
public final class DatabaseTestViewModel_Factory implements Factory<DatabaseTestViewModel> {
  private final Provider<SkinDatabaseRepository> databaseRepositoryProvider;

  public DatabaseTestViewModel_Factory(
      Provider<SkinDatabaseRepository> databaseRepositoryProvider) {
    this.databaseRepositoryProvider = databaseRepositoryProvider;
  }

  @Override
  public DatabaseTestViewModel get() {
    return newInstance(databaseRepositoryProvider.get());
  }

  public static DatabaseTestViewModel_Factory create(
      Provider<SkinDatabaseRepository> databaseRepositoryProvider) {
    return new DatabaseTestViewModel_Factory(databaseRepositoryProvider);
  }

  public static DatabaseTestViewModel newInstance(SkinDatabaseRepository databaseRepository) {
    return new DatabaseTestViewModel(databaseRepository);
  }
}
