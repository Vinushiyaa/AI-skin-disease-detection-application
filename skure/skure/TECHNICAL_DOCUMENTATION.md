# Skure App - Technical Documentation

## Overview
Skure is a medical image analysis app that uses AI to analyze skin conditions and provide medical insights. The app integrates multiple APIs and algorithms for image processing, location services, and AI-powered analysis.

## 🔧 **Algorithms & Techniques Used**

### 1. **Image Processing & Analysis**
- **Computer Vision**: Uses Android CameraX for real-time camera preview and image capture
- **Image Preprocessing**: 
  - Automatic image resizing to 1024px max dimension for optimal API performance
  - JPEG compression (75% quality) to reduce payload size
  - Base64 encoding for API transmission
- **AI Model**: GPT-4o-mini (via OpenRouter) for medical image analysis

### 2. **Location Services**
- **Google Play Services Location API**: For finding nearby hospitals
- **GPS & Network Location**: Hybrid location detection for accuracy
- **Distance Calculation**: Haversine formula for calculating distances between user and hospitals

### 3. **Network & API Management**
- **Retrofit2**: HTTP client for API communication
- **OkHttp3**: Underlying networking with DNS over HTTPS
- **Exponential Backoff**: Retry mechanism for failed API calls
- **Rate Limiting**: Built-in cooldown system to handle API limits

## 🌐 **APIs & Services Used**

### 1. **OpenRouter API (Primary AI Service)**
- **Purpose**: Medical image analysis using GPT-4o-mini
- **Endpoint**: `https://openrouter.ai/api/v1/chat/completions`
- **Model**: `openai/gpt-4o-mini`
- **Features**:
  - Vision capabilities for image analysis
  - Medical-focused prompts for skin condition detection
  - Rate limiting and error handling
- **API Key Required**: Yes (configured in `local.properties`)

### 2. **Google Play Services Location API**
- **Purpose**: Location-based hospital finding
- **Features**:
  - Fine location access
  - Background location updates
  - Geofencing capabilities
- **Permissions**: `ACCESS_FINE_LOCATION`

### 3. **Android CameraX API**
- **Purpose**: Camera functionality for image capture
- **Features**:
  - Real-time camera preview
  - Image capture with high quality
  - Lifecycle-aware camera management
- **Permissions**: `CAMERA`

### 4. **Overpass API**
- **Purpose**: Finding nearby hospitals using OpenStreetMap data
- **Endpoint**: `https://overpass-api.de/api/interpreter`
- **Query Format**: Custom queries for hospital data within a radius
- **Features**:
  - JSON response format
  - Search by amenity type (hospital)
  - Geographic bounding

## 🔑 **Required API Keys & Setup**

### 1. **OpenRouter API Key**
```properties
# Add to local.properties file
OPENROUTER_API_KEY=your_openrouter_api_key_here
```

**How to get OpenRouter API Key:**
1. Visit [OpenRouter.ai](https://openrouter.ai)
2. Sign up for an account
3. Go to API Keys section
4. Create a new API key
5. Add it to your `local.properties` file

### 2. **Google Play Services**
- **Setup**: Automatically configured via Gradle dependencies
- **Requirements**: Google Play Services installed on device
- **No API key needed**: Uses device's Google Play Services

## 🏗️ **Architecture & Design Patterns**

### 1. **MVVM Architecture**
- **ViewModels**: Handle business logic and state management
- **Repository Pattern**: Data access abstraction
- **Dependency Injection**: Hilt for dependency management

### 2. **Reactive Programming**
- **Kotlin Coroutines**: Asynchronous operations
- **StateFlow**: Reactive state management
- **Compose State**: UI state management

### 3. **Modular Design**
- **Feature Modules**: Scan, Chat, Location services
- **Shared Modules**: Network, DI, Utils
- **Clean Architecture**: Separation of concerns

## 📱 **Key Features Implementation**

### 1. **Image Analysis Pipeline**
```
Camera Capture → Image Preprocessing → Base64 Encoding → API Call → AI Analysis → Result Display
```

### 2. **Location-Based Services**
```
Location Permission → GPS/Network Location → Hospital Database → Distance Calculation → Results Display
```

### 3. **Chat Integration**
```
User Input → API Call → AI Response → Message Display → Conversation History
```

### 4. **Food Regulation Advisor**
```
Condition Selection → Dietary Preferences → API Call → Nutrition Advice → Display
```

## 🔒 **Security & Privacy**

### 1. **Data Protection**
- Images are processed locally before API transmission
- No permanent storage of captured images
- API keys stored securely in build configuration

### 2. **Network Security**
- HTTPS for all API communications
- DNS over HTTPS for enhanced privacy
- Certificate pinning (configurable)

### 3. **Permissions**
- Camera: Only when actively scanning
- Location: Only for hospital finding feature
- Network: For API communications

## 🚀 **Performance Optimizations**

### 1. **Image Processing**
- Automatic image resizing to reduce payload
- JPEG compression for faster uploads
- Memory-efficient bitmap handling

### 2. **Network Optimization**
- Connection pooling
- Request/response caching
- Retry mechanisms with exponential backoff

### 3. **UI Performance**
- Compose for efficient UI rendering
- Lazy loading of components
- State management optimization

## 🛠️ **Development Setup**

### 1. **Prerequisites**
- Android Studio Arctic Fox or later
- Android SDK 24+
- Kotlin 1.9.0+
- Gradle 8.0+

### 2. **Dependencies**
- **UI**: Jetpack Compose, Material3
- **Architecture**: ViewModel, LiveData, Navigation
- **Networking**: Retrofit2, OkHttp3
- **DI**: Hilt
- **Camera**: CameraX
- **Location**: Google Play Services
- **Utilities**: Accompanist Permissions

### 3. **Configuration**
1. Clone the repository
2. Add OpenRouter API key to `local.properties`
3. Sync project with Gradle
4. Build and run on device/emulator

## 📊 **Error Handling & Monitoring**

### 1. **API Error Handling**
- HTTP status code mapping
- Rate limit detection and cooldown
- Network connectivity checks
- Retry mechanisms with backoff

### 2. **User Experience**
- Clear error messages
- Loading states
- Graceful degradation
- Offline handling

## 🔮 **Future Enhancements**

### 1. **Potential Improvements**
- Offline image analysis using on-device ML
- Multiple AI model support
- Advanced image preprocessing
- Real-time analysis feedback

### 2. **Additional Features**
- Medical history tracking
- Doctor consultation booking
- Symptom tracking
- Medication reminders

---

## 📞 **Support & Troubleshooting**

### Common Issues:
1. **"openrcuteral" Error**: Usually indicates API key issues or network problems
2. **Camera Not Working**: Check camera permissions
3. **Location Not Found**: Verify location permissions and GPS settings
4. **API Rate Limits**: Wait for cooldown period or upgrade API plan

### Debug Information:
- Check logs for detailed error messages
- Verify API key configuration
- Test network connectivity
- Check device permissions

## 🧩 **Implementation Details**

### Repository Layer
The app uses a repository pattern to abstract data sources:

1. **ImageAnalysisRepository**: Handles image analysis with OpenRouter API
   - Image preprocessing (resize, compress, encode)
   - API call with retry logic and exponential backoff
   - Error handling for various HTTP status codes

2. **TextChatRepository**: Manages chat conversations with AI
   - Maintains conversation history
   - Formats messages for API consumption
   - Handles streaming responses

3. **FoodAdviceRepository**: Provides dietary recommendations
   - Integrates skin conditions with nutrition advice
   - Structures queries for optimal AI responses

### Network Layer
- **Retrofit2** for REST API communication
- **OkHttp3** with DNS over HTTPS for secure connections
- Custom interceptors for logging and error handling
- Gson for JSON serialization/deserialization

### Dependency Injection
- **Hilt** for dependency injection
- Singleton and scoped dependencies
- Android Entry Points for Activities/Fragments
- Module-based dependency configuration

### UI Components
- **Jetpack Compose** for modern UI
- **Material 3** design system
- **Navigation Compose** for screen routing
- **Hilt Navigation** for ViewModel integration

### Data Models
- **UserProfile**: User information and medical history
- **Disease**: Skin condition information with treatment advice
- **Hospital**: Healthcare facility information with location data
- **Product**: Recommended skincare products

### Utilities
- **NetworkUtils**: Network connectivity checking
- **Permissions**: Runtime permission handling
- **AnalysisHistory**: Local storage of analysis results

### Hilt DI Modules
The app uses Hilt for dependency injection with the following structure:
- **@HiltAndroidApp** annotation on the Application class
- **@AndroidEntryPoint** annotation on Activities
- **@HiltViewModel** annotation on ViewModels
- **@Inject** constructors for repositories and utilities
- Automatic provision of Context and other Android dependencies