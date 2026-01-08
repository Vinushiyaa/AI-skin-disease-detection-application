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
public final class AnalysisHistoryViewModel_Factory implements Factory<AnalysisHistoryViewModel> {
  private final Provider<SkinDatabaseRepository> repositoryProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  public AnalysisHistoryViewModel_Factory(Provider<SkinDatabaseRepository> repositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    this.repositoryProvider = repositoryProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public AnalysisHistoryViewModel get() {
    return newInstance(repositoryProvider.get(), authRepositoryProvider.get());
  }

  public static AnalysisHistoryViewModel_Factory create(
      Provider<SkinDatabaseRepository> repositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    return new AnalysisHistoryViewModel_Factory(repositoryProvider, authRepositoryProvider);
  }

  public static AnalysisHistoryViewModel newInstance(SkinDatabaseRepository repository,
      AuthRepository authRepository) {
    return new AnalysisHistoryViewModel(repository, authRepository);
  }
}
