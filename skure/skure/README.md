# Skure - Skin Condition Analysis App

<p align="center">
  <img src="app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" alt="Skure Logo" width="128" height="128">
</p>

<p align="center">
  An Android app that uses AI to analyze skin conditions and provide medical insights.
</p>

<p align="center">
  <a href="#features">Features</a> •
  <a href="#technology-stack">Technology Stack</a> •
  <a href="#setup">Setup</a> •
  <a href="#architecture">Architecture</a> •
  <a href="#license">License</a>
</p>

## Features

### 📸 Image Analysis
- Capture skin condition images with your phone's camera
- AI-powered analysis using GPT-4o-mini via OpenRouter
- Detailed medical descriptions of observed conditions
- First aid recommendations and food charts

### 🏥 Hospital Finder
- Locate nearby hospitals using GPS
- Search for specialized hospitals (e.g., cardiology, neurology, pediatrics)
- View hospital details including address, phone, and hours
- Distance calculation to help find the closest facilities
- Get directions to hospitals via Google Maps integration
- Dual API support: Google Places API for specialized searches and OpenStreetMap for general searches

### 💬 Medical Chatbot
- Ask medical questions about skin conditions
- Get AI-powered responses based on medical knowledge
- Maintain conversation history for context

### 🍎 Food Advisor
- Get dietary recommendations based on skin conditions
- Personalized advice considering dietary preferences and allergies
- Suggestions for foods to eat and avoid

### 👤 Profile Management
- Store user profile and medical history
- Track analysis history
- Customize dietary preferences and allergies

## Technology Stack

### Frontend
- **Jetpack Compose**: Modern Android UI toolkit
- **Material 3**: Design system for consistent UI
- **Navigation Compose**: Screen navigation
- **CameraX**: Camera functionality

### Backend & Architecture
- **MVVM**: Model-View-ViewModel architecture
- **Hilt**: Dependency injection
- **Retrofit2**: HTTP client for API communication
- **OkHttp3**: Networking with DNS over HTTPS
- **Kotlin Coroutines**: Asynchronous programming
- **StateFlow**: Reactive state management

### APIs & Services
- **OpenRouter**: AI model access (GPT-4o-mini)
- **Google Play Services**: Location services
- **OpenStreetMap Overpass API**: General hospital data
- **Google Maps Places API**: Specialized hospital data and details

### Security & Privacy
- HTTPS for all API communications
- Secure API key storage
- Permission-based access controls
- No permanent storage of captured images

## Setup

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24+
- Kotlin 1.9.0+
- JDK 17+

### API Key Configuration

1. Get your OpenRouter API key from [https://openrouter.ai/keys](https://openrouter.ai/keys)
2. Add it to your `local.properties` file:
   ```properties
   OPENROUTER_API_KEY=your_api_key_here
   ```

3. For the Hospital Finder feature, the app uses Overpass API by default (free, no key required):
   - No API key setup needed for basic hospital search functionality
   - To enable specialized hospital searches, get a Google Maps API key:
     - Go to [Google Cloud Console](https://console.cloud.google.com/)
     - Enable the Places API
     - Create an API key
     - Add it to `app/build.gradle.kts`:
     ```kotlin
     buildConfigField("String", "GOOGLE_MAPS_API_KEY", "\"your_google_maps_api_key_here\"")
     ```
   - See [HOSPITAL_SEARCH_FEATURE.md](HOSPITAL_SEARCH_FEATURE.md) for detailed setup instructions

### Building the App
1. Clone the repository
2. Open in Android Studio
3. Sync project with Gradle
4. Build and run on device/emulator

## Architecture

### Project Structure
```
app/
├── src/main/java/com/skure/app/
│   ├── api/           # API interfaces and models
│   ├── chat/          # Chat feature components
│   ├── domain/        # Data models
│   ├── food/          # Food advisor components
│   ├── hospitals/     # Hospital finder components
│   ├── location/      # Location services
│   ├── network/       # Network layer
│   ├── permissions/   # Permission handling
│   ├── repository/    # Data repositories
│   ├── scan/          # Image scanning components
│   ├── ui/            # UI components
│   ├── utils/         # Utility classes
│   ├── MainActivity.kt
│   ├── Nav.kt
│   ├── Screens.kt
│   └── SkureApp.kt
```

### Design Patterns
- **MVVM**: Separation of UI, business logic, and data layers
- **Repository Pattern**: Abstraction of data sources
- **Dependency Injection**: Hilt for managing dependencies
- **Reactive Programming**: StateFlow for state management

### Data Flow
1. **UI Layer**: Compose screens and state management
2. **ViewModel Layer**: Business logic and state coordination
3. **Repository Layer**: Data access and API communication
4. **Network Layer**: HTTP requests and responses

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a pull request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For support, please open an issue on the GitHub repository or contact the development team.