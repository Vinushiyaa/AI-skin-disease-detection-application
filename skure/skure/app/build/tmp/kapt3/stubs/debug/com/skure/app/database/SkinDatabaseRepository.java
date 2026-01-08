package com.skure.app.database;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0017\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ*\u0010\u000f\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00110\u00102\u0006\u0010\u0007\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00102\u0006\u0010\u0007\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0017J*\u0010\u0018\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00110\u00102\u0006\u0010\u0007\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001a\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001a\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u001bJ&\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 H\u0086@\u00a2\u0006\u0002\u0010!J.\u0010\"\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010&J.\u0010\'\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010&J6\u0010*\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010/J\u001e\u00100\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u00102J\u001e\u00103\u001a\u00020\r2\u0006\u00104\u001a\u00020\u00162\u0006\u00105\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u00106R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2 = {"Lcom/skure/app/database/SkinDatabaseRepository;", "", "databaseHelper", "Lcom/skure/app/database/SkinDatabaseHelper;", "(Lcom/skure/app/database/SkinDatabaseHelper;)V", "clearChatHistoryForUser", "", "userId", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearConditionsForUser", "deleteAllPredictionsForUser", "deletePrediction", "predictionId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllConditionsForUser", "", "", "", "getAllPredictionsForUser", "Lcom/skure/app/database/SkinPrediction;", "getAllUsers", "Lcom/skure/app/domain/UserProfile;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getChatHistoryForUser", "getPasswordHashByEmail", "email", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserByEmail", "insertChatMessage", "message", "isUserMessage", "", "(ILjava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertConditionInfo", "conditionName", "description", "treatments", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertFoodRecommendation", "foodName", "benefits", "insertHospitalInfo", "hospitalName", "address", "phone", "distance", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPrediction", "prediction", "(ILcom/skure/app/database/SkinPrediction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertUser", "user", "passwordHash", "(Lcom/skure/app/domain/UserProfile;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SkinDatabaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.database.SkinDatabaseHelper databaseHelper = null;
    
    @javax.inject.Inject()
    public SkinDatabaseRepository(@org.jetbrains.annotations.NotNull()
    com.skure.app.database.SkinDatabaseHelper databaseHelper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertUser(@org.jetbrains.annotations.NotNull()
    com.skure.app.domain.UserProfile user, @org.jetbrains.annotations.NotNull()
    java.lang.String passwordHash, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllUsers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.skure.app.domain.UserProfile>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUserByEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skure.app.domain.UserProfile> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPasswordHashByEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertPrediction(int userId, @org.jetbrains.annotations.NotNull()
    com.skure.app.database.SkinPrediction prediction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllPredictionsForUser(int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.skure.app.database.SkinPrediction>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertChatMessage(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String message, boolean isUserMessage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getChatHistoryForUser(int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.util.Map<java.lang.String, ? extends java.lang.Object>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearChatHistoryForUser(int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertFoodRecommendation(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String foodName, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String benefits, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertConditionInfo(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String conditionName, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String treatments, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllConditionsForUser(int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.util.Map<java.lang.String, ? extends java.lang.Object>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearConditionsForUser(int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertHospitalInfo(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String hospitalName, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String distance, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deletePrediction(long predictionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAllPredictionsForUser(int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
}