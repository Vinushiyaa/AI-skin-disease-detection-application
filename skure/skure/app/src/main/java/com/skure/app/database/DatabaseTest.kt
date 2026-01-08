package com.skure.app.database

import android.content.Context
import android.util.Log

class DatabaseTest(private val context: Context) {
    
    fun testDatabaseOperations(): String {
        return try {
            Log.d("DatabaseTest", "Starting database test")
            
            // Create database helper
            val dbHelper = SkinDatabaseHelper(context)
            
            // Test 1: Insert a user
            val testUser = com.skure.app.domain.UserProfile(
                id = 0,
                fullName = "Database Test User",
                email = "dbtest${System.currentTimeMillis()}@example.com",
                phone = "9876543210",
                medicalHistory = listOf("Allergy", "Asthma"),
                otherHistory = "No other conditions"
            )
            
            val userId = dbHelper.insertUser(testUser, "test_password")
            Log.d("DatabaseTest", "Inserted user with ID: $userId")
            
            if (userId == -1L) {
                return "FAILED: Could not insert user"
            }
            
            // Test 2: Retrieve the user
            val retrievedUser = dbHelper.getUserByEmail(testUser.email)
            if (retrievedUser == null) {
                return "FAILED: Could not retrieve user after insertion"
            }
            
            Log.d("DatabaseTest", "Retrieved user: ${retrievedUser.fullName}, ID: ${retrievedUser.id}")
            
            // Test 3: Insert a prediction for the user
            val testPrediction = com.skure.app.database.SkinPrediction(
                id = 0,
                imageUrl = "test_image_url",
                predictionResult = "Test prediction result",
                confidenceLevel = "High",
                timestamp = System.currentTimeMillis()
            )
            
            val predictionId = dbHelper.insertPrediction(userId.toInt(), testPrediction)
            Log.d("DatabaseTest", "Inserted prediction with ID: $predictionId")
            
            if (predictionId == -1L) {
                return "FAILED: Could not insert prediction"
            }
            
            // Test 4: Retrieve predictions for the user
            val predictions = dbHelper.getAllPredictionsForUser(userId.toInt())
            Log.d("DatabaseTest", "Retrieved ${predictions.size} predictions for user")
            
            if (predictions.isEmpty()) {
                return "FAILED: No predictions found for user after insertion"
            }
            
            // Test 5: Verify the prediction data
            val firstPrediction = predictions[0]
            if (firstPrediction.predictionResult != testPrediction.predictionResult) {
                return "FAILED: Prediction data mismatch"
            }
            
            // Keep database active
            dbHelper.keepDatabaseActive()
            
            "SUCCESS: All database operations completed successfully. User ID: $userId, Prediction ID: $predictionId"
        } catch (e: Exception) {
            Log.e("DatabaseTest", "Error during database test", e)
            "ERROR: ${e.message}"
        }
    }
    
    fun verifyExistingData(): String {
        return try {
            Log.d("DatabaseTest", "Verifying existing database data")
            
            // Create database helper
            val dbHelper = SkinDatabaseHelper(context)
            
            // Get all users
            val allUsers = dbHelper.getAllUsers()
            Log.d("DatabaseTest", "Total users in database: ${allUsers.size}")
            
            // Print all users
            allUsers.forEach { user ->
                Log.d("DatabaseTest", "User: ID=${user.id}, Name=${user.fullName}, Email=${user.email}")
            }
            
            // If we have users, check their predictions
            if (allUsers.isNotEmpty()) {
                val firstUser = allUsers[0]
                val predictions = dbHelper.getAllPredictionsForUser(firstUser.id)
                Log.d("DatabaseTest", "User ${firstUser.email} has ${predictions.size} predictions")
                
                predictions.forEach { prediction ->
                    Log.d("DatabaseTest", "Prediction: ID=${prediction.id}, Result=${prediction.predictionResult}")
                }
                
                return "SUCCESS: Found ${allUsers.size} users, first user has ${predictions.size} predictions"
            }
            
            // Keep database active
            dbHelper.keepDatabaseActive()
            
            "INFO: No users found in database"
        } catch (e: Exception) {
            Log.e("DatabaseTest", "Error verifying existing data", e)
            "ERROR: ${e.message}"
        }
    }
    
    fun closeDatabase() {
        try {
            val dbHelper = SkinDatabaseHelper(context)
            dbHelper.releaseDatabase()
            Log.d("DatabaseTest", "Database connection released")
        } catch (e: Exception) {
            Log.e("DatabaseTest", "Error closing database", e)
        }
    }
}