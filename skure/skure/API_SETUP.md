# OpenRouter API Integration Setup

## How to Use the Image Analysis Feature

### 1. Get Your OpenRouter API Key
1. Go to https://openrouter.ai/keys
2. Sign in to your OpenRouter account (or create one)
3. Click "Create new key"
4. Copy the generated API key

### 2. Set Up the API Key in the App
1. Add your API key to the `local.properties` file:
   ```
   OPENROUTER_API_KEY=your_actual_api_key_here
   ```
2. Sync the project with Gradle
3. Run the app

### 3. How It Works
1. **Take a Photo**: Use the camera to capture an image of the skin condition
2. **AI Analysis**: The image is sent to OpenRouter's GPT-4o-mini model for analysis
3. **Get Results**: Receive a detailed medical description of what the AI observes
4. **Use Results**: The analysis can be used for further processing or saved

### 4. Features
- **Real-time Analysis**: Images are analyzed using OpenRouter's GPT-4o-mini model
- **Medical Focus**: The AI is prompted to focus on medical observations and skin conditions
- **Secure**: API key is stored locally and not shared
- **Error Handling**: Clear error messages if the API call fails
- **Rate Limiting**: Built-in cooldown system to handle API limits

### 5. API Costs
- Each image analysis uses OpenRouter's GPT-4o-mini model
- Current pricing: Very affordable per image (check OpenRouter pricing)
- Monitor your usage at https://openrouter.ai/usage

### 6. Security Notes
- API key is stored in local.properties which is excluded from version control
- All API communications use HTTPS
- Keys are accessed via BuildConfig and not hardcoded in source files

## Code Structure
- `ChatGPTApiService.kt` - API interface for OpenRouter
- `ImageAnalysisRepository.kt` - Repository for image processing
- `ScanViewModel.kt` - ViewModel for scan screen state
- `ScanScreen.kt` - UI for camera and results display
- `BuildConfig` - Secure API key access

## Supported Models
The app currently uses `openai/gpt-4o-mini` model which provides:
- Vision capabilities for image analysis
- Medical knowledge for skin condition detection
- Fast response times
- Cost-effective usage

## API Endpoints
- **Base URL**: https://openrouter.ai/api/v1/
- **Chat Completions**: /chat/completions
- **Model**: openai/gpt-4o-mini (configurable in ChatGPTRequest)

## Error Handling
The app includes comprehensive error handling for:
- Network connectivity issues
- Invalid API keys
- Rate limiting
- Server errors
- Image processing failures

## Retry Mechanism
- Exponential backoff for transient errors
- Automatic retries for rate limiting
- User-friendly cooldown periods