package com.skure.app.network

import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService {
    @GET("/maps/api/place/nearbysearch/json")
    suspend fun searchNearby(
        @Query("location") location: String,
        @Query("radius") radius: Int = 5000,
        @Query("type") type: String = "hospital",
        @Query("keyword") keyword: String? = null,
        @Query("key") apiKey: String
    ): PlacesResponse
    
    @GET("/maps/api/place/details/json")
    suspend fun getPlaceDetails(
        @Query("place_id") placeId: String,
        @Query("fields") fields: String = "name,formatted_phone_number,opening_hours,geometry",
        @Query("key") apiKey: String
    ): PlaceDetailsResponse
}

data class PlacesResponse(
    val results: List<PlaceResult>,
    val status: String
)

data class PlaceResult(
    val place_id: String,
    val name: String,
    val vicinity: String?,
    val geometry: Geometry,
    val types: List<String>? = null
)

data class Geometry(
    val location: Location
)

data class Location(
    val lat: Double,
    val lng: Double
)

data class PlaceDetailsResponse(
    val result: PlaceDetails?,
    val status: String
)

data class PlaceDetails(
    val name: String,
    val formatted_phone_number: String?,
    val opening_hours: OpeningHours?,
    val geometry: Geometry
)

data class OpeningHours(
    val weekday_text: List<String>?
)
