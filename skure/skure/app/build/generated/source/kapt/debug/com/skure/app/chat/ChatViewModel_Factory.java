package com.skure.app.chat;

import com.skure.app.database.SkinDatabaseRepository;
import com.skure.app.repository.AuthRepository;
import com.skure.app.repository.TextChatRepository;
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
public final class ChatViewModel_Factory implements Factory<ChatViewModel> {
  private final Provider<TextChatRepository> repositoryProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<SkinDatabaseRepository> databaseRepositoryProvider;

  public ChatViewModel_Factory(Provider<TextChatRepository> repositoryProvider,
      Provider<AuthRepository> authRepositoryProvider,
      Provider<SkinDatabaseRepository> databaseRepositoryProvider) {
    this.repositoryProvider = repositoryProvider;
    this.authRepositoryProvider = authRepositoryProvider;
    this.databaseRepositoryProvider = databaseRepositoryProvider;
  }

  @Override
  public ChatViewModel get() {
    return newInstance(repositoryProvider.get(), authRepositoryProvider.get(), databaseRepositoryProvider.get());
  }

  public static ChatViewModel_Factory create(Provider<TextChatRepository> repositoryProvider,
      Provider<AuthRepository> authRepositoryProvider,
      Provider<SkinDatabaseRepository> databaseRepositoryProvider) {
    return new ChatViewModel_Factory(repositoryProvider, authRepositoryProvider, databaseRepositoryProvider);
  }

  public static ChatViewModel newInstance(TextChatRepository repository,
      AuthRepository authRepository, SkinDatabaseRepository databaseRepository) {
    return new ChatViewModel(repository, authRepository, databaseRepository);
  }
}
