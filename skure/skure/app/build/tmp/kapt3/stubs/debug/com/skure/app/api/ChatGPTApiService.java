package com.skure.app.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/skure/app/api/ChatGPTApiService;", "", "analyzeImage", "Lretrofit2/Response;", "Lcom/skure/app/api/ChatGPTResponse;", "authorization", "", "request", "Lcom/skure/app/api/ChatGPTRequest;", "(Ljava/lang/String;Lcom/skure/app/api/ChatGPTRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ChatGPTApiService {
    
    @retrofit2.http.Headers(value = {"Content-Type: application/json", "Accept: application/json"})
    @retrofit2.http.POST(value = "v1/chat/completions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object analyzeImage(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String authorization, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.skure.app.api.ChatGPTRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.skure.app.api.ChatGPTResponse>> $completion);
}