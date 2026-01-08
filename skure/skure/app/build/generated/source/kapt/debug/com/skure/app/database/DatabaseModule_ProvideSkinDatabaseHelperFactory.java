package com.skure.app.database;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideSkinDatabaseHelperFactory implements Factory<SkinDatabaseHelper> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideSkinDatabaseHelperFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SkinDatabaseHelper get() {
    return provideSkinDatabaseHelper(contextProvider.get());
  }

  public static DatabaseModule_ProvideSkinDatabaseHelperFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideSkinDatabaseHelperFactory(contextProvider);
  }

  public static SkinDatabaseHelper provideSkinDatabaseHelper(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideSkinDatabaseHelper(context));
  }
}
