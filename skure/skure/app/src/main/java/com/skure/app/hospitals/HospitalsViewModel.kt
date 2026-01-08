package com.skure.app.hospitals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.skure.app.BuildConfig

import com.skure.app.domain.Hospital
import com.skure.app.location.LocationProvider
import com.skure.app.network.OverpassQueries
import com.skure.app.network.RetrofitProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

private const val FALLBACK_LAT = 10.9601  // Karur
private const val FALLBACK_LON = 78.0766
private const val FALLBACK_LOCATION_NAME = "Karur"

data class HospitalsUiState(
    val loading: Boolean = false,
    val hospitals: List<Hospital> = emptyList(),
    val error: String? = null,
    val specialization: String = "",
    val hasSearched: Boolean = false,
    val infoMessage: String? = null
)

class HospitalsViewModel(app: Application) : AndroidViewModel(app) {
    private val locationProvider = LocationProvider(app)

    private val _ui = MutableStateFlow(HospitalsUiState())
    val ui = _ui.asStateFlow()
    
    // Google Maps API Key wired via BuildConfig (see GOOGLE_MAPS_SETUP.md)
    private val mapsApiKey = BuildConfig.GOOGLE_MAPS_API_KEY
    private val hasPlacesApiKey = mapsApiKey.isNotBlank() && !mapsApiKey.startsWith("YOUR_", ignoreCase = true)

    fun loadNearbyHospitals(specialization: String = "") {
        if (_ui.value.loading) return
        _ui.value = _ui.value.copy(
            loading = true,
            error = null,
            specialization = specialization,
            hasSearched = true
        )
        viewModelScope.launch {
            try {
                val infoMessages = mutableListOf<String>()
                val generalSearch = specialization.isBlank()
                val (activeLat, activeLon) = if (generalSearch) {
                    infoMessages += "Showing hospitals near $FALLBACK_LOCATION_NAME."
                    FALLBACK_LAT to FALLBACK_LON
                } else {
                    val loc = locationProvider.getLastKnownOrNull()
                    if (loc != null) {
                        loc.latitude to loc.longitude
                    } else {
                        infoMessages += "Location unavailable—showing hospitals near $FALLBACK_LOCATION_NAME."
                        FALLBACK_LAT to FALLBACK_LON
                    }
                }

                // Use Google Places API only if specialization is provided AND API key is configured
                // Otherwise, always use Overpass API (free and doesn't require API key)
                if (specialization.isNotBlank() && !hasPlacesApiKey) {
                    _ui.value = HospitalsUiState(
                        error = "Specialized search requires a configured Google Maps API key.",
                        specialization = specialization,
                        hasSearched = true
                    )
                    return@launch
                }

                var infoMessage: String? = null

                val parsed = if (specialization.isNotBlank() && hasPlacesApiKey) {
                    val specialized = try {
                        searchBySpecialization(activeLat, activeLon, specialization)
                    } catch (e: Exception) {
                        // If Google Places API fails, fall back to Overpass API
                        android.util.Log.w("HospitalsViewModel", "Google Places API failed, falling back to Overpass API: ${e.message}")
                        emptyList()
                    }

                    if (specialized.isEmpty()) {
                        val fallback = searchGenericHospitals(activeLat, activeLon)
                        val curated = if (fallback.isEmpty()) curatedFallbackHospitalsFor(activeLat, activeLon) else emptyList()
                        when {
                            fallback.isNotEmpty() -> {
                                infoMessages += "No $specialization hospitals found. Showing nearby options instead."
                                infoMessage = infoMessages.joinToString(" ")
                                fallback
                            }
                            curated.isNotEmpty() -> {
                                infoMessages += "No live hospital data available. Showing trusted $FALLBACK_LOCATION_NAME hospitals."
                                infoMessage = infoMessages.joinToString(" ")
                                curated
                            }
                            else -> {
                                _ui.value = HospitalsUiState(
                                    error = "Couldn't find $specialization hospitals nearby.",
                                    specialization = specialization,
                                    hasSearched = true
                                )
                                return@launch
                            }
                        }
                    } else {
                        specialized
                    }
                } else {
                    // Always use Overpass API for general searches (free, no key required)
                    val result = searchGenericHospitals(activeLat, activeLon) { radius ->
                        infoMessages += "Expanded search radius to ${radius / 1000}km to find results."
                    }
                    if (result.isNotEmpty()) {
                        result
                    } else {
                        val curated = curatedFallbackHospitalsFor(activeLat, activeLon)
                        if (curated.isNotEmpty()) {
                            infoMessages += "Showing trusted $FALLBACK_LOCATION_NAME hospitals while we locate real-time data."
                            infoMessage = infoMessages.joinToString(" ")
                            curated
                        } else {
                            _ui.value = HospitalsUiState(
                                error = "No hospitals found nearby. Please try again or adjust your search.",
                                specialization = specialization,
                                hasSearched = true
                            )
                            return@launch
                        }
                    }
                }
                
                if (infoMessage == null && infoMessages.isNotEmpty()) {
                    infoMessage = infoMessages.joinToString(" ")
                }

                _ui.value = HospitalsUiState(
                    hospitals = parsed,
                    specialization = specialization,
                    hasSearched = true,
                    infoMessage = infoMessage
                )
            } catch (t: Throwable) {
                _ui.value = HospitalsUiState(
                    error = t.message ?: "Unknown error occurred",
                    specialization = specialization,
                    hasSearched = true
                )
            }
        }
    }

    private fun curatedFallbackHospitalsFor(
        lat: Double,
        lon: Double
    ): List<Hospital> {
        val curated = listOf(
            Hospital(
                name = "Kaveri Hospital Karur",
                address = "No. 1/45, Srinivasa Naicken Street, Karur 639001",
                latitude = 10.9570,
                longitude = 78.0811,
                distanceKm = haversineKm(lat, lon, 10.9570, 78.0811),
                phone = "+91 4324 233 333",
                hours = "Open 24 hours"
            ),
            Hospital(
                name = "Government Medical College & Hospital, Karur",
                address = "Andankoil East, Karur 639008",
                latitude = 10.9786,
                longitude = 78.0796,
                distanceKm = haversineKm(lat, lon, 10.9786, 78.0796),
                phone = "+91 4324 243 500",
                hours = "Open 24 hours"
            ),
            Hospital(
                name = "Velan Speciality Hospital",
                address = "No. 3, Covai Road, Karur 639002",
                latitude = 10.9619,
                longitude = 78.0902,
                distanceKm = haversineKm(lat, lon, 10.9619, 78.0902),
                phone = "+91 4324 241 212",
                hours = "Open 24 hours"
            ),
            Hospital(
                name = "VMC Speciality Hospital",
                address = "Sengunthapuram Main Rd, Karur 639002",
                latitude = 10.9594,
                longitude = 78.0807,
                distanceKm = haversineKm(lat, lon, 10.9594, 78.0807),
                phone = "+91 4324 230 030",
                hours = "Open 24 hours"
            )
        )
        return curated.sortedBy { it.distanceKm }
    }
    
    private suspend fun searchBySpecialization(lat: Double, lon: Double, specialization: String): List<Hospital> {
        val location = "$lat,$lon"
        val keyword = "$specialization hospital"
        
        val response = RetrofitProvider.places.searchNearby(
            location = location,
            radius = 5000,
            type = "hospital",
            keyword = keyword,
            apiKey = mapsApiKey
        )
        
        if (response.status != "OK" && response.status != "ZERO_RESULTS") {
            throw Exception("Places API error: ${response.status}")
        }
        
        return response.results.map { place ->
            val distanceKm = haversineKm(lat, lon, place.geometry.location.lat, place.geometry.location.lng)
            
            // Try to get more detailed information about the hospital
            var phone: String? = null
            var hours: String? = null
            
            try {
                if (place.place_id.isNotBlank()) {
                    val detailsResponse = RetrofitProvider.places.getPlaceDetails(
                        placeId = place.place_id,
                        fields = "formatted_phone_number,opening_hours",
                        apiKey = mapsApiKey
                    )
                    
                    if (detailsResponse.status == "OK" && detailsResponse.result != null) {
                        phone = detailsResponse.result!!.formatted_phone_number
                        hours = detailsResponse.result!!.opening_hours?.weekday_text?.joinToString("; ")
                    }
                }
            } catch (e: Exception) {
                // Ignore errors when fetching details, we'll just use basic info
            }
            
            Hospital(
                name = place.name,
                address = place.vicinity ?: "",
                latitude = place.geometry.location.lat,
                longitude = place.geometry.location.lng,
                distanceKm = distanceKm,
                phone = phone,
                hours = hours,
                specialization = specialization,
                placeId = place.place_id
            )
        }.sortedBy { it.distanceKm }
    }
    
    private suspend fun searchGenericHospitals(
        lat: Double,
        lon: Double,
        onRadiusExpanded: (Int) -> Unit = {}
    ): List<Hospital> {
        val radii = listOf(5_000, 10_000, 20_000, 35_000, 50_000)
        radii.forEachIndexed { index, radius ->
            try {
                val q = OverpassQueries.hospitalsAround(lat, lon, radius)
                val resp = RetrofitProvider.overpass.query(q)
                val parsed = parseHospitals(resp, lat, lon)
                if (parsed.isNotEmpty()) {
                    if (index > 0) onRadiusExpanded(radius)
                    return parsed
                }
            } catch (e: Exception) {
                android.util.Log.e(
                    "HospitalsViewModel",
                    "Overpass API error (radius=$radius): ${e.message}",
                    e
                )
            }
        }
        return emptyList()
    }

    private fun parseHospitals(json: String, lat: Double, lon: Double): List<Hospital> {
        return try {
            val root: JsonObject = JsonParser.parseString(json).asJsonObject
            val elements = root.getAsJsonArray("elements") ?: return emptyList()
            val list = mutableListOf<Hospital>()
            elements.forEach { el ->
                try {
                    val obj = el.asJsonObject
                    val tags = obj.getAsJsonObject("tags") ?: JsonObject()
                    val name = tags.get("name")?.asString ?: return@forEach
                    val addr = buildString {
                        val street = tags.get("addr:street")?.asString
                        val city = tags.get("addr:city")?.asString
                        val state = tags.get("addr:state")?.asString
                        val postcode = tags.get("addr:postcode")?.asString
                        if (!street.isNullOrBlank()) append(street)
                        if (!city.isNullOrBlank()) append(if (isNotEmpty()) ", $city" else city)
                        if (!state.isNullOrBlank()) append(if (isNotEmpty()) ", $state" else state)
                        if (!postcode.isNullOrBlank()) append(if (isNotEmpty()) " $postcode" else postcode)
                    }.ifBlank { tags.get("addr:full")?.asString ?: "" }

                    val phone = tags.get("phone")?.asString
                        ?: tags.get("contact:phone")?.asString
                    val hours = tags.get("opening_hours")?.asString

                    val latObj = obj.get("lat")?.asDouble
                    val lonObj = obj.get("lon")?.asDouble
                    val center = obj.getAsJsonObject("center")
                    val itemLat = latObj ?: center?.get("lat")?.asDouble ?: return@forEach
                    val itemLon = lonObj ?: center?.get("lon")?.asDouble ?: return@forEach

                    val distanceKm = haversineKm(lat, lon, itemLat, itemLon)
                    list += Hospital(
                        name = name,
                        address = addr,
                        latitude = itemLat,
                        longitude = itemLon,
                        distanceKm = distanceKm,
                        phone = phone,
                        hours = hours
                    )
                } catch (e: Exception) {
                    // Skip invalid elements instead of crashing
                    android.util.Log.w("HospitalsViewModel", "Error parsing hospital element: ${e.message}")
                }
            }
            list.sortedBy { it.distanceKm }
        } catch (e: Exception) {
            // Log the error for debugging
            android.util.Log.e("HospitalsViewModel", "Error parsing Overpass response: ${e.message}", e)
            emptyList()
        }
    }

    private fun haversineKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return R * c
    }
}




