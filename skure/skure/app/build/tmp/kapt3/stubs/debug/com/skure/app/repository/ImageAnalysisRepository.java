package com.skure.app.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ,\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0014"}, d2 = {"Lcom/skure/app/repository/ImageAnalysisRepository;", "", "chatGPTApiService", "Lcom/skure/app/api/ChatGPTApiService;", "networkUtils", "Lcom/skure/app/utils/NetworkUtils;", "databaseRepository", "Lcom/skure/app/database/SkinDatabaseRepository;", "authRepository", "Lcom/skure/app/repository/AuthRepository;", "(Lcom/skure/app/api/ChatGPTApiService;Lcom/skure/app/utils/NetworkUtils;Lcom/skure/app/database/SkinDatabaseRepository;Lcom/skure/app/repository/AuthRepository;)V", "analyzeImage", "Lkotlin/Result;", "", "bitmap", "Landroid/graphics/Bitmap;", "apiKey", "analyzeImage-0E7RQCE", "(Landroid/graphics/Bitmap;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bitmapToBase64", "app_debug"})
public final class ImageAnalysisRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.api.ChatGPTApiService chatGPTApiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.utils.NetworkUtils networkUtils = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.database.SkinDatabaseRepository databaseRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.repository.AuthRepository authRepository = null;
    
    @javax.inject.Inject()
    public ImageAnalysisRepository(@org.jetbrains.annotations.NotNull()
    com.skure.app.api.ChatGPTApiService chatGPTApiService, @org.jetbrains.annotations.NotNull()
    com.skure.app.utils.NetworkUtils networkUtils, @org.jetbrains.annotations.NotNull()
    com.skure.app.database.SkinDatabaseRepository databaseRepository, @org.jetbrains.annotations.NotNull()
    com.skure.app.repository.AuthRepository authRepository) {
        super();
    }
    
    private final java.lang.String bitmapToBase64(android.graphics.Bitmap bitmap) {
        return null;
    }
}