package com.skure.app.chat;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0016\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0017"}, d2 = {"Lcom/skure/app/chat/ChatViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/skure/app/repository/TextChatRepository;", "authRepository", "Lcom/skure/app/repository/AuthRepository;", "databaseRepository", "Lcom/skure/app/database/SkinDatabaseRepository;", "(Lcom/skure/app/repository/TextChatRepository;Lcom/skure/app/repository/AuthRepository;Lcom/skure/app/database/SkinDatabaseRepository;)V", "_ui", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/skure/app/chat/ChatUi;", "ui", "Lkotlinx/coroutines/flow/StateFlow;", "getUi", "()Lkotlinx/coroutines/flow/StateFlow;", "clearChatHistory", "", "loadChatHistory", "send", "text", "", "apiKey", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ChatViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.repository.TextChatRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.database.SkinDatabaseRepository databaseRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.skure.app.chat.ChatUi> _ui = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.skure.app.chat.ChatUi> ui = null;
    
    @javax.inject.Inject()
    public ChatViewModel(@org.jetbrains.annotations.NotNull()
    com.skure.app.repository.TextChatRepository repository, @org.jetbrains.annotations.NotNull()
    com.skure.app.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.skure.app.database.SkinDatabaseRepository databaseRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.skure.app.chat.ChatUi> getUi() {
        return null;
    }
    
    public final void loadChatHistory() {
    }
    
    public final void send(@org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey) {
    }
    
    public final void clearChatHistory() {
    }
}