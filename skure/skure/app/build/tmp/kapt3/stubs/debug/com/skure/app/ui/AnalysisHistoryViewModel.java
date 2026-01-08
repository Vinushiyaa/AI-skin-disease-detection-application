package com.skure.app.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u000e\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\tJ\u0006\u0010\u0012\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/skure/app/ui/AnalysisHistoryViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/skure/app/database/SkinDatabaseRepository;", "authRepository", "Lcom/skure/app/repository/AuthRepository;", "(Lcom/skure/app/database/SkinDatabaseRepository;Lcom/skure/app/repository/AuthRepository;)V", "predictionsCache", "", "Lcom/skure/app/database/SkinPrediction;", "clearAllPredictions", "", "deletePrediction", "id", "", "getPredictions", "insertTestPrediction", "prediction", "refreshPredictions", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AnalysisHistoryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.database.SkinDatabaseRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.skure.app.database.SkinPrediction> predictionsCache;
    
    @javax.inject.Inject()
    public AnalysisHistoryViewModel(@org.jetbrains.annotations.NotNull()
    com.skure.app.database.SkinDatabaseRepository repository, @org.jetbrains.annotations.NotNull()
    com.skure.app.repository.AuthRepository authRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skure.app.database.SkinPrediction> getPredictions() {
        return null;
    }
    
    public final void refreshPredictions() {
    }
    
    public final void deletePrediction(long id) {
    }
    
    public final void clearAllPredictions() {
    }
    
    public final void insertTestPrediction(@org.jetbrains.annotations.NotNull()
    com.skure.app.database.SkinPrediction prediction) {
    }
}