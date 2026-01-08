package com.skure.app.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0013"}, d2 = {"Lcom/skure/app/network/RetrofitProvider;", "", "()V", "httpClient", "Lokhttp3/OkHttpClient;", "getHttpClient", "()Lokhttp3/OkHttpClient;", "httpClient$delegate", "Lkotlin/Lazy;", "overpass", "Lcom/skure/app/network/OverpassService;", "getOverpass", "()Lcom/skure/app/network/OverpassService;", "overpass$delegate", "places", "Lcom/skure/app/network/PlacesApiService;", "getPlaces", "()Lcom/skure/app/network/PlacesApiService;", "places$delegate", "app_debug"})
public final class RetrofitProvider {
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy httpClient$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy overpass$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy places$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.skure.app.network.RetrofitProvider INSTANCE = null;
    
    private RetrofitProvider() {
        super();
    }
    
    private final okhttp3.OkHttpClient getHttpClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.skure.app.network.OverpassService getOverpass() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.skure.app.network.PlacesApiService getPlaces() {
        return null;
    }
}