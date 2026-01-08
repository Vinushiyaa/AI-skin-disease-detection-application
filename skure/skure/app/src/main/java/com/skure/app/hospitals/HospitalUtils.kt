package com.skure.app.hospitals

import android.content.Context
import android.content.Intent
import android.net.Uri

object HospitalUtils {
    /**
     * Opens Google Maps to show directions to the specified hospital
     */
    fun openDirectionsInMaps(context: Context, latitude: Double, longitude: Double, hospitalName: String) {
        val uri = Uri.parse("google.navigation:q=$latitude,$longitude")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        
        // If Google Maps is installed, use it
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            // Fall back to browser-based directions
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/dir/?api=1&destination=$latitude,$longitude&destination_place_id=$hospitalName")
            )
            context.startActivity(browserIntent)
        }
    }
    
    /**
     * Opens Google Maps to show the hospital location
     */
    fun openLocationInMaps(context: Context, latitude: Double, longitude: Double, hospitalName: String) {
        val label = Uri.encode(hospitalName)
        val uri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($label)")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        
        // If Google Maps is installed, use it
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            // Fall back to browser-based map
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/search/?api=1&query=$latitude,$longitude")
            )
            context.startActivity(browserIntent)
        }
    }
}