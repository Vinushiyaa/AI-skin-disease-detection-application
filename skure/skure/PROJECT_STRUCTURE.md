# Skure App - Project Structure

## Overview
This document provides a detailed overview of the Skure app's project structure and organization.

## Project Directory Structure

```
skure/
├── app/                          # Main application module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/skure/app/  # Source code
│   │   │   │   ├── api/            # API interfaces and models
│   │   │   │   ├── chat/           # Chat feature components
│   │   │   │   ├── di/             # Dependency injection modules
│   │   │   │   ├── domain/         # Data models
│   │   │   │   ├── food/           # Food advisor components
│   │   │   │   ├── hospitals/      # Hospital finder components
│   │   │   │   ├── location/       # Location services
│   │   │   │   ├── network/        # Network layer
│   │   │   │   ├── permissions/    # Permission handling
│   │   │   │   ├── repository/     # Data repositories
│   │   │   │   ├── scan/           # Image scanning components
│   │   │   │   ├── ui/             # UI components
│   │   │   │   ├── utils/          # Utility classes
│   │   │   │   ├── MainActivity.kt # Main activity
│   │   │   │   ├── Nav.kt          # Navigation setup
│   │   │   │   ├── Screens.kt      # Screen composables
│   │   │   │   └── SkureApp.kt     # Application class
│   │   │   ├── res/                # Resources
│   │   │   │   ├── drawable/       # Drawables
│   │   │   │   ├── mipmap-*/       # Launcher icons
│   │   │   │   ├── values/         # String, color, style resources
│   │   │   │   └── xml/            # XML configurations
│   │   │   └── AndroidManifest.xml # Application manifest
│   │   └── test/                   # Unit tests
│   ├── build.gradle.kts            # Module build configuration
│   └── gradle.properties           # Module-specific Gradle properties
├── gradle/                         # Gradle wrapper files
├── build.gradle.kts                # Root build configuration
├── gradle.properties               # Project-wide Gradle properties
├── gradlew                         # Gradle wrapper script (Unix)
├── gradlew.bat                     # Gradle wrapper script (Windows)
├── local.properties                # Local configuration (not in version control)
├── settings.gradle.kts             # Project settings and module declarations
├── README.md                       # Project overview and setup instructions
├── LICENSE                         # License information
├── API_KEY_SETUP.md                # API key setup instructions
├── API_SETUP.md                    # API integration documentation
└── TECHNICAL_DOCUMENTATION.md      # Detailed technical documentation
```

## Module Breakdown

### 1. API Module (`api/`)
Contains API interfaces and data models for external services.

**Key Files:**
- `ChatGPTApiService.kt`: Retrofit interface for OpenRouter API
- Data models for API requests and responses

### 2. Chat Module (`chat/`)
Implements the medical chatbot functionality.

**Key Files:**
- `ChatViewModel.kt`: ViewModel for chat state management

### 3. Dependency Injection Module (`di/`)
Configures Hilt dependency injection.

**Key Files:**
- `NetworkModule.kt`: Network client and service configuration

### 4. Domain Module (`domain/`)
Contains core data models used throughout the application.

**Key Files:**
- `Models.kt`: Disease, Hospital, and Product data models
- `UserProfile.kt`: User profile data model

### 5. Food Module (`food/`)
Implements the dietary advisor feature.

**Key Files:**
- `FoodViewModel.kt`: ViewModel for food advice state management

### 6. Hospitals Module (`hospitals/`)
Implements the hospital finder functionality.

**Key Files:**
- `HospitalsViewModel.kt`: ViewModel for hospital data management

### 7. Location Module (`location/`)
Handles location services and permissions.

**Key Files:**
- `LocationProvider.kt`: Wrapper for Google Play Location Services

### 8. Network Module (`network/`)
Contains network service definitions and utilities.

**Key Files:**
- `OverpassService.kt`: Interface for OpenStreetMap Overpass API
- `RetrofitProvider.kt`: Retrofit client configuration

### 9. Permissions Module (`permissions/`)
Handles runtime permission requests.

**Key Files:**
- `RequirePermissions.kt`: Composable for requesting permissions

### 10. Repository Module (`repository/`)
Implements the repository pattern for data access.

**Key Files:**
- `ImageAnalysisRepository.kt`: Image analysis with AI
- `TextChatRepository.kt`: Chat functionality
- `FoodAdviceRepository.kt`: Dietary recommendations

### 11. Scan Module (`scan/`)
Implements the image scanning and analysis feature.

**Key Files:**
- `ScanScreen.kt`: Camera UI and image capture
- `ScanViewModel.kt`: ViewModel for scan state management

### 12. UI Module (`ui/`)
Contains shared UI components and theme definitions.

### 13. Utilities Module (`utils/`)
Provides utility functions used across the application.

**Key Files:**
- `AnalysisHistory.kt`: Local storage of analysis results
- `NetworkUtils.kt`: Network connectivity checking

## Key Application Components

### MainActivity.kt
The main entry point of the application. Sets up the Compose UI and Hilt dependency injection.

### Nav.kt
Defines the navigation graph for the application using Jetpack Navigation Compose.

### Screens.kt
Contains all the screen composables for the different features of the app.

### SkureApp.kt
The Application class with Hilt setup.

## Build Configuration

### build.gradle.kts (App Module)
- Configures Android application settings
- Defines dependencies
- Sets up BuildConfig for API key access
- Configures Compose and Hilt plugins

### build.gradle.kts (Root)
- Configures project-wide plugin versions
- Sets up plugin management

### settings.gradle.kts
- Defines module inclusion
- Configures repository settings

### gradle.properties
- Configures Gradle and Kotlin options
- Sets AndroidX and JVM flags

## Resources

### res/
- `drawable/`: Image resources
- `mipmap-*/`: Application launcher icons
- `values/`: Strings, colors, themes, and styles
- `xml/`: Network security configuration

## Documentation Files

### README.md
Project overview, features, setup instructions, and basic usage information.

### LICENSE
MIT License information.

### API_KEY_SETUP.md
Detailed instructions for configuring API keys.

### API_SETUP.md
Technical documentation for API integration.

### TECHNICAL_DOCUMENTATION.md
Comprehensive technical documentation including architecture, implementation details, and troubleshooting.

### PROJECT_STRUCTURE.md
This document - provides detailed information about the project organization.