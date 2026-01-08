package com.skure.app.hospitals;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J(\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\rJ&\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010!\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J,\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\rH\u0082@\u00a2\u0006\u0002\u0010#J:\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0014\b\u0002\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\'\u0012\u0004\u0012\u00020\u001e0&H\u0082@\u00a2\u0006\u0002\u0010(R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082D\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006)"}, d2 = {"Lcom/skure/app/hospitals/HospitalsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_ui", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/skure/app/hospitals/HospitalsUiState;", "hasPlacesApiKey", "", "locationProvider", "Lcom/skure/app/location/LocationProvider;", "mapsApiKey", "", "ui", "Lkotlinx/coroutines/flow/StateFlow;", "getUi", "()Lkotlinx/coroutines/flow/StateFlow;", "curatedFallbackHospitalsFor", "", "Lcom/skure/app/domain/Hospital;", "lat", "", "lon", "haversineKm", "lat1", "lon1", "lat2", "lon2", "loadNearbyHospitals", "", "specialization", "parseHospitals", "json", "searchBySpecialization", "(DDLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchGenericHospitals", "onRadiusExpanded", "Lkotlin/Function1;", "", "(DDLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class HospitalsViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skure.app.location.LocationProvider locationProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.skure.app.hospitals.HospitalsUiState> _ui = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.skure.app.hospitals.HospitalsUiState> ui = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String mapsApiKey = "AIzaSyA0Lqwretw9-_6dDOUl5NqGDiGNfq5vNts";
    private final boolean hasPlacesApiKey = false;
    
    public HospitalsViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.skure.app.hospitals.HospitalsUiState> getUi() {
        return null;
    }
    
    public final void loadNearbyHospitals(@org.jetbrains.annotations.NotNull()
    java.lang.String specialization) {
    }
    
    private final java.util.List<com.skure.app.domain.Hospital> curatedFallbackHospitalsFor(double lat, double lon) {
        return null;
    }
    
    private final java.lang.Object searchBySpecialization(double lat, double lon, java.lang.String specialization, kotlin.coroutines.Continuation<? super java.util.List<com.skure.app.domain.Hospital>> $completion) {
        return null;
    }
    
    private final java.lang.Object searchGenericHospitals(double lat, double lon, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onRadiusExpanded, kotlin.coroutines.Continuation<? super java.util.List<com.skure.app.domain.Hospital>> $completion) {
        return null;
    }
    
    private final java.util.List<com.skure.app.domain.Hospital> parseHospitals(java.lang.String json, double lat, double lon) {
        return null;
    }
    
    private final double haversineKm(double lat1, double lon1, double lat2, double lon2) {
        return 0.0;
    }
}