package com.skure.app.repository;

import com.skure.app.api.ChatGPTApiService;
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
public final class TextChatRepository_Factory implements Factory<TextChatRepository> {
  private final Provider<ChatGPTApiService> chatGPTApiServiceProvider;

  private final Provider<NetworkUtils> networkUtilsProvider;

  public TextChatRepository_Factory(Provider<ChatGPTApiService> chatGPTApiServiceProvider,
      Provider<NetworkUtils> networkUtilsProvider) {
    this.chatGPTApiServiceProvider = chatGPTApiServiceProvider;
    this.networkUtilsProvider = networkUtilsProvider;
  }

  @Override
  public TextChatRepository get() {
    return newInstance(chatGPTApiServiceProvider.get(), networkUtilsProvider.get());
  }

  public static TextChatRepository_Factory create(
      Provider<ChatGPTApiService> chatGPTApiServiceProvider,
      Provider<NetworkUtils> networkUtilsProvider) {
    return new TextChatRepository_Factory(chatGPTApiServiceProvider, networkUtilsProvider);
  }

  public static TextChatRepository newInstance(ChatGPTApiService chatGPTApiService,
      NetworkUtils networkUtils) {
    return new TextChatRepository(chatGPTApiService, networkUtils);
  }
}
