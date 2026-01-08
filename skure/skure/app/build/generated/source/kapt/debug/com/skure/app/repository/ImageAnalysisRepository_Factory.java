package com.skure.app.repository;

import com.skure.app.api.ChatGPTApiService;
import com.skure.app.database.SkinDatabaseRepository;
import com.skure.app.utils.NetworkUtils;
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
public final class ImageAnalysisRepository_Factory implements Factory<ImageAnalysisRepository> {
  private final Provider<ChatGPTApiService> chatGPTApiServiceProvider;

  private final Provider<NetworkUtils> networkUtilsProvider;

  private final Provider<SkinDatabaseRepository> databaseRepositoryProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  public ImageAnalysisRepository_Factory(Provider<ChatGPTApiService> chatGPTApiServiceProvider,
      Provider<NetworkUtils> networkUtilsProvider,
      Provider<SkinDatabaseRepository> databaseRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    this.chatGPTApiServiceProvider = chatGPTApiServiceProvider;
    this.networkUtilsProvider = networkUtilsProvider;
    this.databaseRepositoryProvider = databaseRepositoryProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public ImageAnalysisRepository get() {
    return newInstance(chatGPTApiServiceProvider.get(), networkUtilsProvider.get(), databaseRepositoryProvider.get(), authRepositoryProvider.get());
  }

  public static ImageAnalysisRepository_Factory create(
      Provider<ChatGPTApiService> chatGPTApiServiceProvider,
      Provider<NetworkUtils> networkUtilsProvider,
      Provider<SkinDatabaseRepository> databaseRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    return new ImageAnalysisRepository_Factory(chatGPTApiServiceProvider, networkUtilsProvider, databaseRepositoryProvider, authRepositoryProvider);
  }

  public static ImageAnalysisRepository newInstance(ChatGPTApiService chatGPTApiService,
      NetworkUtils networkUtils, SkinDatabaseRepository databaseRepository,
      AuthRepository authRepository) {
    return new ImageAnalysisRepository(chatGPTApiService, networkUtils, databaseRepository, authRepository);
  }
}
