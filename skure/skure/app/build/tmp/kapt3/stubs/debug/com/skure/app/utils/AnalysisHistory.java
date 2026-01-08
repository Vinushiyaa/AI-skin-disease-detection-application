package com.skure.app.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0004J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\b\u001a\u00020\tJ\u001e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J&\u0010\u0012\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/skure/app/utils/AnalysisHistory;", "", "()V", "HISTORY_DIR", "", "HISTORY_META", "clearAllHistory", "", "context", "Landroid/content/Context;", "deleteAnalysis", "id", "loadAllAnalyses", "", "Lcom/skure/app/utils/AnalysisRecord;", "loadLastAnalysis", "saveHistoryList", "records", "saveLastAnalysis", "sourceImage", "Ljava/io/File;", "description", "timestamp", "", "app_debug"})
public final class AnalysisHistory {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String HISTORY_DIR = "analysis_history";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String HISTORY_META = "history_list.json";
    @org.jetbrains.annotations.NotNull()
    public static final com.skure.app.utils.AnalysisHistory INSTANCE = null;
    
    private AnalysisHistory() {
        super();
    }
    
    public final void saveLastAnalysis(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.io.File sourceImage, @org.jetbrains.annotations.NotNull()
    java.lang.String description, long timestamp) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.skure.app.utils.AnalysisRecord loadLastAnalysis(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skure.app.utils.AnalysisRecord> loadAllAnalyses(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    public final void deleteAnalysis(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    public final void clearAllHistory(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final void saveHistoryList(android.content.Context context, java.util.List<com.skure.app.utils.AnalysisRecord> records) {
    }
}