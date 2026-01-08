package com.skure.app.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\bJB\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\u00052\b\b\u0003\u0010\f\u001a\u00020\r2\b\b\u0003\u0010\u000e\u001a\u00020\u00052\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/skure/app/network/PlacesApiService;", "", "getPlaceDetails", "Lcom/skure/app/network/PlaceDetailsResponse;", "placeId", "", "fields", "apiKey", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchNearby", "Lcom/skure/app/network/PlacesResponse;", "location", "radius", "", "type", "keyword", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface PlacesApiService {
    
    @retrofit2.http.GET(value = "/maps/api/place/nearbysearch/json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchNearby(@retrofit2.http.Query(value = "location")
    @org.jetbrains.annotations.NotNull()
    java.lang.String location, @retrofit2.http.Query(value = "radius")
    int radius, @retrofit2.http.Query(value = "type")
    @org.jetbrains.annotations.NotNull()
    java.lang.String type, @retrofit2.http.Query(value = "keyword")
    @org.jetbrains.annotations.Nullable()
    java.lang.String keyword, @retrofit2.http.Query(value = "key")
    @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skure.app.network.PlacesResponse> $completion);
    
    @retrofit2.http.GET(value = "/maps/api/place/details/json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPlaceDetails(@retrofit2.http.Query(value = "place_id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String placeId, @retrofit2.http.Query(value = "fields")
    @org.jetbrains.annotations.NotNull()
    java.lang.String fields, @retrofit2.http.Query(value = "key")
    @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skure.app.network.PlaceDetailsResponse> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}