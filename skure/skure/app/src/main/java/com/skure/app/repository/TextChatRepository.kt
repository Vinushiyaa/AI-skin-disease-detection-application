package com.skure.app.repository

import com.skure.app.api.ChatGPTApiService
import com.skure.app.api.ChatGPTContent
import com.skure.app.api.ChatGPTMessage
import com.skure.app.api.ChatGPTRequest
import com.skure.app.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TextChatRepository @Inject constructor(
    private val chatGPTApiService: ChatGPTApiService,
    private val networkUtils: NetworkUtils
) {
    suspend fun askMedicalQuestion(history: List<Pair<String, String>>, apiKey: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            if (!networkUtils.isNetworkAvailable()) {
                return@withContext Result.failure(Exception("No internet connection. ${networkUtils.getNetworkStatusMessage()}"))
            }

            val messages = history.map { (role, text) ->
                ChatGPTMessage(
                    role = role,
                    content = listOf(
                        ChatGPTContent(type = "text", text = text)
                    )
                )
            }

            val systemMessage = ChatGPTMessage(
                role = "system",
                content = listOf(
                    ChatGPTContent(
                        type = "text",
                        text = "You are a cautious, helpful medical assistant. Answer health questions with evidence-based guidance, include when to seek professional care, and avoid definitive diagnoses."
                    )
                )
            )

            val request = ChatGPTRequest(
                messages = listOf(systemMessage) + messages,
                max_tokens = 800
            )

            val maxAttempts = 4
            var attempt = 0
            while (attempt < maxAttempts) {
                attempt++
                val response = chatGPTApiService.analyzeImage(
                    authorization = "Bearer $apiKey",
                    request = request
                )
                if (response.isSuccessful) {
                    val content = response.body()?.choices?.firstOrNull()?.message?.content
                    if (content != null) return@withContext Result.success(content)
                    return@withContext Result.failure(Exception("No content received from API"))
                }
                if (response.code() == 429 || response.code() == 500 || response.code() == 502 || response.code() == 503 || response.code() == 504) {
                    val backoffMs = 800L * (1 shl (attempt - 1))
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
                    return@withContext Result.failure(Exception("$errorMessage ${errorBody ?: ""}"))
                }
            }
            Result.failure(Exception("Rate limited by API. Please wait a moment and try again."))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
