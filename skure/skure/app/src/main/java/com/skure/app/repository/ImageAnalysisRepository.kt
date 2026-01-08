package com.skure.app.repository

import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import com.skure.app.api.ChatGPTApiService
import com.skure.app.api.ChatGPTContent
import com.skure.app.api.ChatGPTImageUrl
import com.skure.app.api.ChatGPTMessage
import com.skure.app.api.ChatGPTRequest
import com.skure.app.database.SkinDatabaseRepository
import com.skure.app.database.SkinPrediction
import com.skure.app.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageAnalysisRepository @Inject constructor(
    private val chatGPTApiService: ChatGPTApiService,
    private val networkUtils: NetworkUtils,
    private val databaseRepository: SkinDatabaseRepository,
    private val authRepository: AuthRepository
) {
    suspend fun analyzeImage(bitmap: Bitmap, apiKey: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            // Check network connectivity first
            if (!networkUtils.isNetworkAvailable()) {
                return@withContext Result.failure(Exception("No internet connection. ${networkUtils.getNetworkStatusMessage()}"))
            }
            
            // Get current user
            val currentUser = authRepository.getCurrentUser()
            if (currentUser == null) {
                return@withContext Result.failure(Exception("User not logged in"))
            }
            
            val base64Image = bitmapToBase64(bitmap)
            
            val request = ChatGPTRequest(
                messages = listOf(
                    ChatGPTMessage(
                        role = "user",
                        content = listOf(
                            ChatGPTContent(
                                type = "text",
                                text = """You are analyzing a close-up skin image. In 3-4 concise sentences: (1) Summarize key visible features (color, pattern/texture, distribution). (2) Name 1–3 likely dermatological conditions the image might represent (e.g., eczema, contact dermatitis, psoriasis, acne, fungal infection), with brief confidence terms like low/medium/high. (3) Include a short caution that this is not a diagnosis. Keep it clear and precise."""
                            ),
                            ChatGPTContent(
                                type = "image_url",
                                image_url = ChatGPTImageUrl(
                                    url = "data:image/jpeg;base64,$base64Image"
                                )
                            )
                        )
                    )
                )
            )
            
            println("DEBUG: Making API call to OpenAI with key: ${apiKey.take(10)}...")
            val maxAttempts = 4
            var attempt = 0
            var lastError: Exception? = null
            while (attempt < maxAttempts) {
                attempt++
                val response = chatGPTApiService.analyzeImage(
                    authorization = "Bearer $apiKey",
                    request = request
                )
            
                println("DEBUG: API response code: ${response.code()}")
                println("DEBUG: API response message: ${response.message()}")
                if (!response.isSuccessful) {
                    val errorBody = response.errorBody()?.string()
                    println("DEBUG: API error body: $errorBody")
                }
            
                if (response.isSuccessful) {
                    val content = response.body()?.choices?.firstOrNull()?.message?.content
                    if (content != null) {
                        Log.d("ImageAnalysis", "API Response received: $content")
                        // Save to database with user ID
                        val prediction = SkinPrediction(
                            imageUrl = "data:image/jpeg;base64,$base64Image",
                            predictionResult = content,
                            confidenceLevel = "Medium" // This would ideally come from the API response
                        )
                        val predictionId = databaseRepository.insertPrediction(currentUser.id, prediction)
                        Log.d("ImageAnalysis", "Saved prediction to database with ID: $predictionId for user ID: ${currentUser.id}")
                        
                        return@withContext Result.success(content)
                    } else {
                        Log.e("ImageAnalysis", "No content received from API")
                        return@withContext Result.failure(Exception("No content received from API"))
                    }
                }

                // Handle rate limit and transient errors with backoff
                if (response.code() == 429 || response.code() == 500 || response.code() == 502 || response.code() == 503 || response.code() == 504) {
                    val backoffMs = 800L * (1 shl (attempt - 1))
                    Log.d("ImageAnalysis", "Transient error ${response.code()}, backing off ${backoffMs}ms (attempt $attempt/$maxAttempts)")
                    delay(backoffMs)
                    continue
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = when (response.code()) {
                        401 -> "Invalid API key. Please check your OpenRouter API key."
                        403 -> "Access forbidden. Check your API key permissions."
                        404 -> "API endpoint not found."
                        429 -> "Rate limit exceeded. Please wait before trying again."
                        500 -> "Server error. Please try again later."
                        else -> "API call failed: ${response.code()} - ${response.message()}"
                    }
                    Log.e("ImageAnalysis", "API Error: $errorMessage - ${errorBody ?: ""}")
                    return@withContext Result.failure(Exception("$errorMessage ${errorBody ?: ""}"))
                }
            }

            // If we exhausted retries
            Result.failure(Exception("Rate limited by API. Please wait a moment and try again."))
        } catch (e: Exception) {
            println("DEBUG: Exception caught: ${e.javaClass.simpleName}: ${e.message}")
            e.printStackTrace()
            Result.failure(e)
        }
    }
    
    private fun bitmapToBase64(bitmap: Bitmap): String {
        // Downscale large images to ~1024px max dimension to keep payload small
        val maxSize = 1024
        val (w, h) = bitmap.width to bitmap.height
        val scale = maxOf(w, h).let { if (it > maxSize) maxSize.toFloat() / it else 1f }
        val resized = if (scale < 1f) {
            val newW = (w * scale).toInt()
            val newH = (h * scale).toInt()
            Bitmap.createScaledBitmap(bitmap, newW, newH, true)
        } else bitmap
        val outputStream = ByteArrayOutputStream()
        resized.compress(Bitmap.CompressFormat.JPEG, 75, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }
}