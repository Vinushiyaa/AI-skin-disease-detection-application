package com.skure.app.hospitals;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bJ&\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b\u00a8\u0006\r"}, d2 = {"Lcom/skure/app/hospitals/HospitalUtils;", "", "()V", "openDirectionsInMaps", "", "context", "Landroid/content/Context;", "latitude", "", "longitude", "hospitalName", "", "openLocationInMaps", "app_debug"})
public final class HospitalUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.skure.app.hospitals.HospitalUtils INSTANCE = null;
    
    private HospitalUtils() {
        super();
    }
    
    /**
     * Opens Google Maps to show directions to the specified hospital
     */
    public final void openDirectionsInMaps(@org.jetbrains.annotations.NotNull()
    android.content.Context context, double latitude, double longitude, @org.jetbrains.annotations.NotNull()
    java.lang.String hospitalName) {
    }
    
    /**
     * Opens Google Maps to show the hospital location
     */
    public final void openLocationInMaps(@org.jetbrains.annotations.NotNull()
    android.content.Context context, double latitude, double longitude, @org.jetbrains.annotations.NotNull()
    java.lang.String hospitalName) {
    }
}