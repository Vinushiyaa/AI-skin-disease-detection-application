package com.skure.app.conditions;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/skure/app/conditions/ConditionsViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/skure/app/repository/AuthRepository;", "databaseRepository", "Lcom/skure/app/database/SkinDatabaseRepository;", "(Lcom/skure/app/repository/AuthRepository;Lcom/skure/app/database/SkinDatabaseRepository;)V", "saveConditionViewed", "", "conditionKey", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ConditionsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.database.SkinDatabaseRepository databaseRepository = null;
    
    @javax.inject.Inject()
    public ConditionsViewModel(@org.jetbrains.annotations.NotNull()
    com.skure.app.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.skure.app.database.SkinDatabaseRepository databaseRepository) {
        super();
    }
    
    public final void saveConditionViewed(@org.jetbrains.annotations.NotNull()
    java.lang.String conditionKey) {
    }
}