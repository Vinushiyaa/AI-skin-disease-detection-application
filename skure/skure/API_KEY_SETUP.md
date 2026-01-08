# API Key Setup Instructions

## Step 1: Get Your OpenRouter API Key
1. Go to: https://openrouter.ai/keys
2. Sign in to your OpenRouter account (or create one)
3. Click "Create new key"
4. Copy the generated key

## Step 2: Add Your API Key to the Project
1. Open the file: `local.properties` (in the root of the project)
2. Add the following line:
   ```
   OPENROUTER_API_KEY=your_actual_api_key_here
   ```
3. Replace `your_actual_api_key_here` with your actual API key
4. Example:
   ```
   OPENROUTER_API_KEY=sk-or-1234567890abcdef1234567890abcdef
   ```

## Step 3: Build and Run
1. Sync the project with Gradle
2. Build and run the app
3. Go to Scan screen
4. The app will automatically use the configured API key for image analysis

## Example:
```properties
# In local.properties file:
OPENROUTER_API_KEY=sk-or-1234567890abcdef1234567890abcdef
```

## Security Note:
- Never commit your real API key to version control
- The API key is stored in local.properties which is in .gitignore
- For production apps, consider using more secure storage mechanisms
- The current setup is secure for development/testing

## Troubleshooting:
- If you see "API Key Missing" error, check that the key is correctly added to local.properties
- Ensure there are no extra spaces or characters in the key
- Restart Android Studio after adding the key to ensure it's picked up