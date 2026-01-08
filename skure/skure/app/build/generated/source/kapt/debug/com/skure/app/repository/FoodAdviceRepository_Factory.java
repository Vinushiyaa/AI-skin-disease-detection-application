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
public final class FoodAdviceRepository_Factory implements Factory<FoodAdviceRepository> {
  private final Provider<ChatGPTApiService> chatGPTApiServiceProvider;

  private final Provider<NetworkUtils> networkUtilsProvider;

  private final Provider<SkinDatabaseRepository> databaseRepositoryProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  public FoodAdviceRepository_Factory(Provider<ChatGPTApiService> chatGPTApiServiceProvider,
      Provider<NetworkUtils> networkUtilsProvider,
      Provider<SkinDatabaseRepository> databaseRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    this.chatGPTApiServiceProvider = chatGPTApiServiceProvider;
    this.networkUtilsProvider = networkUtilsProvider;
    this.databaseRepositoryProvider = databaseRepositoryProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public FoodAdviceRepository get() {
    return newInstance(chatGPTApiServiceProvider.get(), networkUtilsProvider.get(), databaseRepositoryProvider.get(), authRepositoryProvider.get());
  }

  public static FoodAdviceRepository_Factory create(
      Provider<ChatGPTApiService> chatGPTApiServiceProvider,
      Provider<NetworkUtils> networkUtilsProvider,
      Provider<SkinDatabaseRepository> databaseRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    return new FoodAdviceRepository_Factory(chatGPTApiServiceProvider, networkUtilsProvider, databaseRepositoryProvider, authRepositoryProvider);
  }

  public static FoodAdviceRepository newInstance(ChatGPTApiService chatGPTApiService,
      NetworkUtils networkUtils, SkinDatabaseRepository databaseRepository,
      AuthRepository authRepository) {
    return new FoodAdviceRepository(chatGPTApiService, networkUtils, databaseRepository, authRepository);
  }
}
