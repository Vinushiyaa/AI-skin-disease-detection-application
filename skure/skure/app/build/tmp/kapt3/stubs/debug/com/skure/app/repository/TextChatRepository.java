package com.skure.app.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J>\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0018\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\f0\u000b2\u0006\u0010\r\u001a\u00020\tH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0010"}, d2 = {"Lcom/skure/app/repository/TextChatRepository;", "", "chatGPTApiService", "Lcom/skure/app/api/ChatGPTApiService;", "networkUtils", "Lcom/skure/app/utils/NetworkUtils;", "(Lcom/skure/app/api/ChatGPTApiService;Lcom/skure/app/utils/NetworkUtils;)V", "askMedicalQuestion", "Lkotlin/Result;", "", "history", "", "Lkotlin/Pair;", "apiKey", "askMedicalQuestion-0E7RQCE", "(Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class TextChatRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.api.ChatGPTApiService chatGPTApiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.utils.NetworkUtils networkUtils = null;
    
    @javax.inject.Inject()
    public TextChatRepository(@org.jetbrains.annotations.NotNull()
    com.skure.app.api.ChatGPTApiService chatGPTApiService, @org.jetbrains.annotations.NotNull()
    com.skure.app.utils.NetworkUtils networkUtils) {
        super();
    }
}