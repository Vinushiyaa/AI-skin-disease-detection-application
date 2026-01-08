package com.skure.app.network

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OverpassService {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("/api/interpreter")
    suspend fun query(@Body body: String): String
}

object OverpassQueries {
    fun hospitalsAround(lat: Double, lon: Double, radiusMeters: Int = 5000): String {
        // Validate inputs
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
            throw IllegalArgumentException("Invalid coordinates: lat must be between -90 and 90, lon must be between -180 and 180")
        }
        
        if (radiusMeters <= 0 || radiusMeters > 50000) {
            throw IllegalArgumentException("Invalid radius: must be between 1 and 50000 meters")
        }
        
        val q = """
            [out:json][timeout:25];
            (
              node["amenity"="hospital"](around:$radiusMeters,$lat,$lon);
              way["amenity"="hospital"](around:$radiusMeters,$lat,$lon);
              relation["amenity"="hospital"](around:$radiusMeters,$lat,$lon);
            );
            out center tags;
        """.trimIndent()
        return "data=${q.encodeURLForForm()}"
    }
}

private fun String.encodeURLForForm(): String =
    this.replace("\n", " ")
        .replace("\r", " ")
        .trim()
        .let { java.net.URLEncoder.encode(it, Charsets.UTF_8.name()).replace("+", "%20") }




