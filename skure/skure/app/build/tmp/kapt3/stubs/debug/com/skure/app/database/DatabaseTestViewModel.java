package com.skure.app.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\bJ\"\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\bJ \u0010\r\u001a\u00020\u00062\u0018\u0010\u0007\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0004\u0012\u00020\u00060\bJ \u0010\u0010\u001a\u00020\u00062\u0018\u0010\u0007\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u000e\u0012\u0004\u0012\u00020\u00060\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/skure/app/database/DatabaseTestViewModel;", "Landroidx/lifecycle/ViewModel;", "databaseRepository", "Lcom/skure/app/database/SkinDatabaseRepository;", "(Lcom/skure/app/database/SkinDatabaseRepository;)V", "createTestUser", "", "callback", "Lkotlin/Function1;", "", "createTestUserAndVerify", "databaseHelper", "Lcom/skure/app/database/SkinDatabaseHelper;", "getAllPredictions", "", "Lcom/skure/app/database/SkinPrediction;", "getAllUsers", "Lcom/skure/app/domain/UserProfile;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DatabaseTestViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.database.SkinDatabaseRepository databaseRepository = null;
    
    @javax.inject.Inject()
    public DatabaseTestViewModel(@org.jetbrains.annotations.NotNull()
    com.skure.app.database.SkinDatabaseRepository databaseRepository) {
        super();
    }
    
    public final void createTestUser(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> callback) {
    }
    
    public final void createTestUserAndVerify(@org.jetbrains.annotations.NotNull()
    com.skure.app.database.SkinDatabaseHelper databaseHelper, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> callback) {
    }
    
    public final void getAllUsers(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.skure.app.domain.UserProfile>, kotlin.Unit> callback) {
    }
    
    public final void getAllPredictions(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.skure.app.database.SkinPrediction>, kotlin.Unit> callback) {
    }
}