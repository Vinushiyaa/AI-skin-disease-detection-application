package com.skure.app.database;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class SkinDatabaseRepository_Factory implements Factory<SkinDatabaseRepository> {
  private final Provider<SkinDatabaseHelper> databaseHelperProvider;

  public SkinDatabaseRepository_Factory(Provider<SkinDatabaseHelper> databaseHelperProvider) {
    this.databaseHelperProvider = databaseHelperProvider;
  }

  @Override
  public SkinDatabaseRepository get() {
    return newInstance(databaseHelperProvider.get());
  }

  public static SkinDatabaseRepository_Factory create(
      Provider<SkinDatabaseHelper> databaseHelperProvider) {
    return new SkinDatabaseRepository_Factory(databaseHelperProvider);
  }

  public static SkinDatabaseRepository newInstance(SkinDatabaseHelper databaseHelper) {
    return new SkinDatabaseRepository(databaseHelper);
  }
}
