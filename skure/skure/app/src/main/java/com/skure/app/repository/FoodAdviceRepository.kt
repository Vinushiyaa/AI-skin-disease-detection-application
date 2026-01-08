package com.skure.app.repository

import com.skure.app.api.ChatGPTApiService
import com.skure.app.api.ChatGPTContent
import com.skure.app.api.ChatGPTMessage
import com.skure.app.api.ChatGPTRequest
import com.skure.app.database.FoodRecommendation
import com.skure.app.database.SkinDatabaseRepository
import com.skure.app.repository.AuthRepository
import com.skure.app.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

data class FoodAdvice(
    val eat: List<String> = emptyList(),
    val avoid: List<String> = emptyList(),
    val substitutes: List<String> = emptyList(),
    val lifestyle: List<String> = emptyList(),
    val disclaimer: String? = null
)

@Singleton
class FoodAdviceRepository @Inject constructor(
    private val chatGPTApiService: ChatGPTApiService,
    private val networkUtils: NetworkUtils,
    private val databaseRepository: SkinDatabaseRepository,
    private val authRepository: AuthRepository
) {
    suspend fun getAdvice(condition: String, preferences: String, allergies: String, apiKey: String): Result<FoodAdvice> = withContext(Dispatchers.IO) {
        try {
            if (!networkUtils.isNetworkAvailable()) {
                return@withContext Result.failure(Exception("No internet connection. ${networkUtils.getNetworkStatusMessage()}"))
            }

            // Get current user
            val currentUser = authRepository.getCurrentUser()
            if (currentUser == null) {
                return@withContext Result.failure(Exception("User not logged in"))
            }

            val system = ChatGPTMessage(
                role = "system",
                content = listOf(
                    ChatGPTContent(
                        type = "text",
                        text = "You are a medical nutrition assistant. Provide safe, general diet guidance."
                    )
                )
            )
            val user = ChatGPTMessage(
                role = "user",
                content = listOf(
                    ChatGPTContent(
                        type = "text",
                        text = (
                            "Provide diet guidance for condition: '" + condition + "'. " +
                            "Consider preferences (dietary pattern, culture): '" + preferences + "' and allergies/other conditions: '" + allergies + "'. " +
                            "Respond ONLY in JSON with keys: 'eat', 'avoid', 'substitutes', 'lifestyle' (arrays of short items), and 'disclaimer' (short sentence). " +
                            "Example: {\"eat\":[\"...\"],\"avoid\":[\"...\"],\"substitutes\":[\"...\"],\"lifestyle\":[\"...\"],\"disclaimer\":\"...\"}"
                        )
                    )
                )
            )

            val request = ChatGPTRequest(
                messages = listOf(system, user),
                max_tokens = 400
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
                    if (content != null) {
                        val foodAdvice = parseAdvice(content)
                        
                        // Save to database
                        val recommendation = FoodRecommendation(
                            userId = currentUser.id,
                            condition = condition,
                            eat = foodAdvice.eat,
                            avoid = foodAdvice.avoid,
                            substitutes = foodAdvice.substitutes,
                            lifestyle = foodAdvice.lifestyle,
                            disclaimer = foodAdvice.disclaimer
                        )
                        val recommendationId = databaseRepository.insertFoodRecommendation(
                            currentUser.id, 
                            "Food recommendations for ${recommendation.condition}",
                            "Eat: ${recommendation.eat.joinToString(", ")}\nAvoid: ${recommendation.avoid.joinToString(", ")}",
                            "Substitutes: ${recommendation.substitutes.joinToString(", ")}\nLifestyle: ${recommendation.lifestyle.joinToString(", ")}"
                        )
                        
                        // Save condition information to the conditions table
                        databaseRepository.insertConditionInfo(
                            currentUser.id,
                            recommendation.condition,
                            "Food-related condition information",
                            "Dietary recommendations: ${recommendation.eat.joinToString(", ")}"
                        )
                        
                        return@withContext Result.success(foodAdvice)
                    }
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

    private fun parseAdvice(text: String): FoodAdvice {
        return try {
            val obj = JSONObject(text)
            val eatArray = obj.optJSONArray("eat")
            val avoidArray = obj.optJSONArray("avoid")
            val subsArray = obj.optJSONArray("substitutes")
            val lifeArray = obj.optJSONArray("lifestyle")
            val eat = mutableListOf<String>()
            val avoid = mutableListOf<String>()
            val subs = mutableListOf<String>()
            val life = mutableListOf<String>()
            if (eatArray != null) for (i in 0 until eatArray.length()) eat.add(eatArray.optString(i))
            if (avoidArray != null) for (i in 0 until avoidArray.length()) avoid.add(avoidArray.optString(i))
            if (subsArray != null) for (i in 0 until subsArray.length()) subs.add(subsArray.optString(i))
            if (lifeArray != null) for (i in 0 until lifeArray.length()) life.add(lifeArray.optString(i))
            val disclaimer = if (obj.has("disclaimer") && !obj.isNull("disclaimer")) obj.getString("disclaimer") else null
            FoodAdvice(eat = eat, avoid = avoid, substitutes = subs, lifestyle = life, disclaimer = disclaimer)
        } catch (_: Exception) {
            // Fallback: try to split by lines
            val lines = text.lines()
            val eat = lines.filter { it.contains("eat", ignoreCase = true) }
            val avoid = lines.filter { it.contains("avoid", ignoreCase = true) }
            FoodAdvice(eat = eat, avoid = avoid, substitutes = emptyList(), lifestyle = emptyList(), disclaimer = null)
        }
    }
}