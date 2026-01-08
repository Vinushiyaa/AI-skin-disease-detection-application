# Hospital Search Feature

## Overview
The hospital search feature allows users to find nearby hospitals based on their current location. By default, the app uses the free Overpass API (OpenStreetMap) for hospital searches. Users can optionally enable Google Places API for specialized hospital searches (e.g., cardiology, neurology, pediatrics).

## Features
1. **Location-Based Search**: Automatically detects user's current location to find nearby hospitals
2. **Specialization Search**: Allows users to search for hospitals specializing in specific medical fields (requires Google Places API key)
3. **Detailed Information**: Displays hospital name, address, distance, phone number, and hours
4. **Map Integration**: Provides buttons to get directions or view hospital location on Google Maps
5. **Flexible API Support**: Uses free Overpass API by default; Google Places API available for specialized searches

## Implementation Details

### Architecture
- **ViewModel**: [HospitalsViewModel](file:///c:/skure/skure/app/src/main/java/com/skure/app/hospitals/HospitalsViewModel.kt) handles business logic and API calls
- **UI**: [HospitalsScreen](file:///c:/skure/skure/app/src/main/java/com/skure/app/Screens.kt#L403-L549) in [Screens.kt](file:///c:/skure/skure/app/src/main/java/com/skure/app/Screens.kt) displays the user interface
- **Utilities**: [HospitalUtils](file:///c:/skure/skure/app/src/main/java/com/skure/app/hospitals/HospitalUtils.kt) provides map integration functions
- **Models**: [Hospital](file:///c:/skure/skure/app/src/main/java/com/skure/app/domain/Models.kt#L18-L28) data class in [Models.kt](file:///c:/skure/skure/app/src/main/java/com/skure/app/domain/Models.kt)

### APIs Used
1. **Google Places API**: For specialized hospital searches (optional, requires API key)
2. **Overpass API (OpenStreetMap)**: For general hospital searches (default, free, no key required)

## Setup Instructions

### Google Maps API Setup (Optional)
The app works with the free Overpass API by default. Google Places API is only needed for specialized hospital searches.
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

### Add API Key to App (Optional)
To enable specialized hospital searches, add your Google Maps API key. If you skip this step, the app will use the free Overpass API for all searches.

#### Method 1: Build Configuration (Recommended)
1. Open `app/build.gradle.kts`
2. Find the line with `buildConfigField("String", "GOOGLE_MAPS_API_KEY", ...)`
3. Replace `"YOUR_GOOGLE_MAPS_API_KEY_HERE"` with your actual API key:
   ```kotlin
   buildConfigField("String", "GOOGLE_MAPS_API_KEY", "\"AIzaSyBxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\"")
   ```
4. Update `app/src/main/java/com/skure/app/hospitals/HospitalsViewModel.kt` to use the BuildConfig field:
   ```kotlin
   private val mapsApiKey = BuildConfig.GOOGLE_MAPS_API_KEY
   ```

#### Method 2: Direct Replacement (Alternative)
1. Open `app/src/main/java/com/skure/app/hospitals/HospitalsViewModel.kt`
2. Find the line with `private val mapsApiKey = ""`
3. Replace it with:
   ```kotlin
   private val mapsApiKey = "AIzaSyBxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
   ```

### Restrict Your API Key (Recommended)
For security, restrict your API key:
1. Click on your API key in the Credentials page
2. Under "Application restrictions":
   - Select "Android apps"
   - Click "Add package name and fingerprint"
   - Package name: `com.skure.app`
   - Get your SHA-1 fingerprint by running: `./gradlew signingReport`
3. Under "API restrictions":
   - Select "Restrict key"
   - Check "Places API"
4. Click "Save"

### Enable Billing (Required)
Google Maps Platform requires billing to be enabled:
1. In Google Cloud Console, go to "Billing"
2. Link a billing account to your project
3. Note: Google provides $200 free credit per month, which is sufficient for most development and moderate production use

## Usage

### Finding All Hospitals (Default Behavior)
1. Open the app and navigate to the "Nearby Hospitals" section
2. Leave the specialization field blank
3. Click "Find All Hospitals"
4. The app will use Overpass API to find nearby hospitals (free, no key required)

### Finding Specialized Hospitals (Optional)
1. Open the app and navigate to the "Nearby Hospitals" section
2. Enter a specialization in the text field (e.g., "cardiology", "pediatrics", "neurology")
3. Click "Search Specialized Hospitals"
4. If Google Places API is configured, the app will use it to find specialized hospitals
5. If Google Places API is not configured, the app will fall back to Overpass API

### Getting Directions
1. After searching for hospitals, tap the "Directions" button next to any hospital
2. This will open Google Maps with directions to that hospital

### Viewing on Map
1. After searching for hospitals, tap the "View Map" button next to any hospital
2. This will open Google Maps showing the hospital's location

## Examples of Specializations
- kidney
- cardiology / heart
- neurology / neuro
- orthopedic / orthopedics
- pediatric / children
- oncology / cancer
- ophthalmology / eye
- dermatology / skin
- psychiatry / mental health

## Error Handling
The app includes comprehensive error handling:
- Location permission errors
- Network connectivity issues
- API errors (invalid key, quota exceeded, etc.)
- HTTP 400/500 errors from Overpass API
- Invalid response parsing
- No results found scenarios
- Graceful degradation when APIs fail

## Pricing
Google Places API pricing (as of 2024):
- **Nearby Search**: $32 per 1000 requests
- **Free tier**: $200 credit per month = ~6,250 free searches per month

For most users, this will remain free under the monthly credit.

## Troubleshooting

### Error: "REQUEST_DENIED"
- This error occurs when Google Places API is not properly configured
- The app now automatically falls back to Overpass API when this happens
- To fix this error, either:
  1. Add a valid Google Maps API key (see Setup Instructions), or
  2. Leave the specialization field blank to use Overpass API (free, no key required)

### Error: "INVALID_REQUEST"
- The location or search parameters may be invalid
- Check that location permissions are granted
- The app now handles this gracefully by falling back to Overpass API

### No Results Found
- Try different specialization keywords
- Increase search radius (currently set to 5000 meters)
- Some areas may have limited hospital data

### HTTP 400 Bad Request Errors
- These errors typically occur due to malformed API requests
- The app now automatically handles these errors by:
  1. Logging the error for debugging
  2. Falling back to the Overpass API
  3. Providing user-friendly error messages
- If you continue to experience issues, try:
  1. Checking your internet connection
  2. Verifying location services are enabled
  3. Restarting the app

## Default Behavior: Overpass API Only
The app now uses Overpass API by default for all searches:
- No API key setup required
- Completely free to use
- Works for general hospital searches
- Note: Overpass API doesn't support specialization filtering (that requires Google Places API)

## Technical Notes
- The app uses Overpass API by default for all hospital searches (free, no key required)
- Google Places API is optional for specialized searches (requires API key)
- Automatic fallback from Google Places API to Overpass API when API key is missing or invalid
- Location permissions are required for the feature to work
- All network requests are handled asynchronously to prevent UI blocking
- Distance calculations use the Haversine formula for accuracy