package com.skure.app.location;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0087@\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u0004\u0018\u00010\bH\u0083@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/skure/app/location/LocationProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "fused", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "getLastKnownOrNull", "Landroid/location/Location;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestCurrentLocation", "app_debug"})
public final class LocationProvider {
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gms.location.FusedLocationProviderClient fused = null;
    
    public LocationProvider(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLastKnownOrNull(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super android.location.Location> $completion) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final java.lang.Object requestCurrentLocation(kotlin.coroutines.Continuation<? super android.location.Location> $completion) {
        return null;
    }
}