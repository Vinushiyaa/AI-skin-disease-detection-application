# Google Maps API Setup for Hospital Search

## Overview
This app uses Google Maps Places API to search for specialized hospitals near the user's location. When a user enters a specialization (e.g., "kidney", "cardiology", "neurology"), the app searches for hospitals with that specialization nearby.

## Setup Instructions

### 1. Get a Google Maps API Key

1. Go to the [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select an existing one
3. Enable the **Places API**:
   - Navigate to "APIs & Services" > "Library"
   - Search for "Places API"
   - Click "Enable"
4. Create an API key:
   - Navigate to "APIs & Services" > "Credentials"
   - Click "Create Credentials" > "API Key"
   - Copy your API key

### 2. Restrict Your API Key (Recommended)

For security, restrict your API key:
1. Click on your API key in the Credentials page
2. Under "Application restrictions":
   - Select "Android apps"
   - Click "Add package name and fingerprint"
   - Package name: `com.skure.app`
   - Get your SHA-1 fingerprint by running:
     ```
     gradlew signingReport
     ```
3. Under "API restrictions":
   - Select "Restrict key"
   - Check "Places API"
4. Click "Save"

### 3. Add the API Key to Your App

Open the file: `app/src/main/java/com/skure/app/hospitals/HospitalsViewModel.kt`

Find this line (around line 34):
```kotlin
private val mapsApiKey = "YOUR_GOOGLE_MAPS_API_KEY_HERE"
```

Replace `YOUR_GOOGLE_MAPS_API_KEY_HERE` with your actual API key:
```kotlin
private val mapsApiKey = "AIzaSyBxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
```

### 4. Enable Billing (Required)

Google Maps Platform requires billing to be enabled:
1. In Google Cloud Console, go to "Billing"
2. Link a billing account to your project
3. Note: Google provides $200 free credit per month, which is sufficient for most development and moderate production use

## How It Works

### Searching for All Hospitals
- Leave the specialization field blank
- Click "Find All Hospitals"
- The app uses Overpass API (OpenStreetMap) to find nearby hospitals

### Searching for Specialized Hospitals
- Enter a specialization (e.g., "kidney", "heart", "cancer", "orthopedic")
- Click "Search Specialized Hospitals"
- The app uses Google Places API with the keyword: "[specialization] hospital"

### Examples of Specializations
- kidney
- cardiology / heart
- neurology / neuro
- orthopedic / orthopedics
- pediatric / children
- oncology / cancer
- ophthalmology / eye
- dermatology / skin
- psychiatry / mental health

## Pricing

Google Places API pricing (as of 2024):
- **Nearby Search**: $32 per 1000 requests
- **Free tier**: $200 credit per month = ~6,250 free searches per month

For most users, this will remain free under the monthly credit.

## Troubleshooting

### Error: "REQUEST_DENIED"
- Check if Places API is enabled in your Google Cloud project
- Verify your API key is correct
- Ensure billing is enabled

### Error: "INVALID_REQUEST"
- The location or search parameters may be invalid
- Check that location permissions are granted

### No Results Found
- Try different specialization keywords
- Increase search radius (currently set to 5000 meters)
- Some areas may have limited hospital data

## Alternative: Use Overpass API Only

If you don't want to use Google Maps API:
- Leave the specialization field blank always
- The app will use Overpass API (OpenStreetMap) which is free
- Note: Overpass API doesn't support specialization filtering

## Support

For issues with Google Maps API, see:
- [Places API Documentation](https://developers.google.com/maps/documentation/places/web-service)
- [Google Cloud Support](https://cloud.google.com/support)
