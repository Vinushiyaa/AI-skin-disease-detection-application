package com.skure.app.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    private val httpClient: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    val overpass: OverpassService by lazy {
        Retrofit.Builder()
            .baseUrl("https://overpass-api.de")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OverpassService::class.java)
    }
    
    val places: PlacesApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlacesApiService::class.java)
    }
}




