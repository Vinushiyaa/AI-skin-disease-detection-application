package com.skure.app.di;

import com.skure.app.api.ChatGPTApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideChatGPTApiServiceFactory implements Factory<ChatGPTApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideChatGPTApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ChatGPTApiService get() {
    return provideChatGPTApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideChatGPTApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideChatGPTApiServiceFactory(retrofitProvider);
  }

  public static ChatGPTApiService provideChatGPTApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideChatGPTApiService(retrofit));
  }
}
