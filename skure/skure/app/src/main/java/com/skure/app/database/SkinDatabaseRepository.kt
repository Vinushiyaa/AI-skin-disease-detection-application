package com.skure.app.database

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import com.skure.app.domain.UserProfile

@Singleton
class SkinDatabaseRepository @Inject constructor(
    private val databaseHelper: SkinDatabaseHelper
) {

    // User operations
    suspend fun insertUser(user: UserProfile, passwordHash: String): Long = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Inserting user: ${user.email}")
        val id = databaseHelper.insertUser(user, passwordHash)
        Log.d("SkinDatabaseRepository", "Inserted user with ID: $id")
        return@withContext id
    }
    
    suspend fun getAllUsers(): List<UserProfile> = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Getting all users")
        val users = databaseHelper.getAllUsers()
        Log.d("SkinDatabaseRepository", "Retrieved ${users.size} users")
        return@withContext users
    }
    
    suspend fun getUserByEmail(email: String): UserProfile? = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Getting user by email: $email")
        val user = databaseHelper.getUserByEmail(email)
        Log.d("SkinDatabaseRepository", if (user != null) "Retrieved user with email: $email" else "User not found with email: $email")
        return@withContext user
    }
    
    suspend fun getPasswordHashByEmail(email: String): String? = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Getting password hash for email: $email")
        val passwordHash = databaseHelper.getPasswordHashByEmail(email)
        Log.d("SkinDatabaseRepository", if (passwordHash != null) "Retrieved password hash for email: $email" else "Password hash not found for email: $email")
        return@withContext passwordHash
    }
    
    // Skin prediction operations
    suspend fun insertPrediction(userId: Int, prediction: SkinPrediction): Long = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Inserting prediction for user ID: $userId")
        val id = databaseHelper.insertPrediction(userId, prediction)
        Log.d("SkinDatabaseRepository", "Inserted prediction with ID: $id for user ID: $userId")
        return@withContext id
    }

    suspend fun getAllPredictionsForUser(userId: Int): List<SkinPrediction> = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Getting all predictions for user ID: $userId")
        val predictions = databaseHelper.getAllPredictionsForUser(userId)
        Log.d("SkinDatabaseRepository", "Retrieved ${predictions.size} predictions for user ID: $userId")
        return@withContext predictions
    }
    
    // Chatbot conversation operations
    suspend fun insertChatMessage(userId: Int, message: String, isUserMessage: Boolean): Long = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Inserting chat message for user ID: $userId")
        val id = databaseHelper.insertChatMessage(userId, message, isUserMessage)
        Log.d("SkinDatabaseRepository", "Inserted chat message with ID: $id for user ID: $userId")
        return@withContext id
    }
    
    suspend fun getChatHistoryForUser(userId: Int): List<Map<String, Any?>> = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Getting chat history for user ID: $userId")
        val chatHistory = databaseHelper.getChatHistoryForUser(userId)
        Log.d("SkinDatabaseRepository", "Retrieved ${chatHistory.size} chat messages for user ID: $userId")
        return@withContext chatHistory
    }
    
    suspend fun clearChatHistoryForUser(userId: Int): Int = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Clearing chat history for user ID: $userId")
        val result = databaseHelper.clearChatHistoryForUser(userId)
        Log.d("SkinDatabaseRepository", "Cleared $result chat messages for user ID: $userId")
        return@withContext result
    }
    
    // Food recommendation operations
    suspend fun insertFoodRecommendation(userId: Int, foodName: String, description: String, benefits: String): Long = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Inserting food recommendation for user ID: $userId")
        val id = databaseHelper.insertFoodRecommendation(userId, foodName, description, benefits)
        Log.d("SkinDatabaseRepository", "Inserted food recommendation with ID: $id for user ID: $userId")
        return@withContext id
    }
    
    // Condition information operations
    suspend fun insertConditionInfo(userId: Int, conditionName: String, description: String, treatments: String): Long = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Inserting condition info for user ID: $userId")
        val id = databaseHelper.insertConditionInfo(userId, conditionName, description, treatments)
        Log.d("SkinDatabaseRepository", "Inserted condition info with ID: $id for user ID: $userId")
        return@withContext id
    }
    
    suspend fun getAllConditionsForUser(userId: Int): List<Map<String, Any?>> = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Getting all conditions for user ID: $userId")
        val conditions = databaseHelper.getAllConditionsForUser(userId)
        Log.d("SkinDatabaseRepository", "Retrieved ${conditions.size} conditions for user ID: $userId")
        return@withContext conditions
    }
    
    suspend fun clearConditionsForUser(userId: Int): Int = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Clearing conditions for user ID: $userId")
        val result = databaseHelper.clearConditionsForUser(userId)
        Log.d("SkinDatabaseRepository", "Cleared $result conditions for user ID: $userId")
        return@withContext result
    }
    
    // Nearby hospital operations
    suspend fun insertHospitalInfo(userId: Int, hospitalName: String, address: String, phone: String, distance: String): Long = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Inserting hospital info for user ID: $userId")
        val id = databaseHelper.insertHospitalInfo(userId, hospitalName, address, phone, distance)
        Log.d("SkinDatabaseRepository", "Inserted hospital info with ID: $id for user ID: $userId")
        return@withContext id
    }
    
    // Delete operations
    suspend fun deletePrediction(predictionId: Long): Int = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Deleting prediction with ID: $predictionId")
        val result = databaseHelper.deletePrediction(predictionId)
        Log.d("SkinDatabaseRepository", "Deleted $result prediction(s) with ID: $predictionId")
        return@withContext result
    }
    
    suspend fun deleteAllPredictionsForUser(userId: Int): Int = withContext(Dispatchers.IO) {
        Log.d("SkinDatabaseRepository", "Deleting all predictions for user ID: $userId")
        val result = databaseHelper.deleteAllPredictionsForUser(userId)
        Log.d("SkinDatabaseRepository", "Deleted all $result prediction(s) for user ID: $userId")
        return@withContext result
    }
}