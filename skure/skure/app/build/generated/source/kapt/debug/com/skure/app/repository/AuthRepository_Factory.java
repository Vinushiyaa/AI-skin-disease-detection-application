package com.skure.app.repository;

import android.content.Context;
import com.skure.app.database.SkinDatabaseRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AuthRepository_Factory implements Factory<AuthRepository> {
  private final Provider<Context> contextProvider;

  private final Provider<SkinDatabaseRepository> databaseRepositoryProvider;

  public AuthRepository_Factory(Provider<Context> contextProvider,
      Provider<SkinDatabaseRepository> databaseRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.databaseRepositoryProvider = databaseRepositoryProvider;
  }

  @Override
  public AuthRepository get() {
    return newInstance(contextProvider.get(), databaseRepositoryProvider.get());
  }

  public static AuthRepository_Factory create(Provider<Context> contextProvider,
      Provider<SkinDatabaseRepository> databaseRepositoryProvider) {
    return new AuthRepository_Factory(contextProvider, databaseRepositoryProvider);
  }

  public static AuthRepository newInstance(Context context,
      SkinDatabaseRepository databaseRepository) {
    return new AuthRepository(context, databaseRepository);
  }
}
