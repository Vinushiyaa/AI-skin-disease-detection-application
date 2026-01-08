package com.skure.app.location

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.coroutines.resume

class LocationProvider(context: Context) {
    private val fused: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getLastKnownOrNull(): android.location.Location? {
        // First try to get last known location
        val lastKnown = suspendCancellableCoroutine { cont ->
            fused.lastLocation
                .addOnSuccessListener { cont.resume(it) }
                .addOnFailureListener { cont.resume(null) }
        }
        
        // If last known location exists, return it
        if (lastKnown != null) {
            return lastKnown
        }
        
        // Otherwise, request a fresh location update
        return requestCurrentLocation()
    }
    
    @SuppressLint("MissingPermission")
    private suspend fun requestCurrentLocation(): android.location.Location? {
        return withTimeoutOrNull(10000) {
            suspendCancellableCoroutine { cont ->
                val locationRequest = LocationRequest.Builder(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    5000
                ).build()
                
                val callback = object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult) {
                        fused.removeLocationUpdates(this)
                        cont.resume(result.lastLocation)
                    }
                }
                
                fused.requestLocationUpdates(
                    locationRequest,
                    callback,
                    Looper.getMainLooper()
                )
                
                cont.invokeOnCancellation {
                    fused.removeLocationUpdates(callback)
                }
            }
        }
    }
}




