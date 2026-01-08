package com.skure.app.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\rJ\b\u0010\u0011\u001a\u00020\nH\u0002J\u0006\u0010\u0012\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/skure/app/database/DatabaseInitializer;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "dbHelper", "Lcom/skure/app/database/SkinDatabaseHelper;", "timer", "Ljava/util/Timer;", "closeDatabase", "", "initializeDatabase", "queryAllUsers", "", "Lcom/skure/app/domain/UserProfile;", "queryDatabase", "Lcom/skure/app/database/SkinPrediction;", "startPeriodicQueries", "stopPeriodicQueries", "app_debug"})
public final class DatabaseInitializer {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable()
    private com.skure.app.database.SkinDatabaseHelper dbHelper;
    @org.jetbrains.annotations.Nullable()
    private java.util.Timer timer;
    
    public DatabaseInitializer(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void initializeDatabase() {
    }
    
    private final void startPeriodicQueries() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skure.app.database.SkinPrediction> queryDatabase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skure.app.domain.UserProfile> queryAllUsers() {
        return null;
    }
    
    public final void stopPeriodicQueries() {
    }
    
    public final void closeDatabase() {
    }
}