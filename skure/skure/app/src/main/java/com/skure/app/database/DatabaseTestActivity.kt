package com.skure.app.database

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.skure.app.domain.UserProfile
import com.skure.app.database.SkinPrediction

class DatabaseTestActivity : Activity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Run database test
        testDatabaseOperations()
    }
    
    private fun testDatabaseOperations() {
        try {
            Log.d("DatabaseTestActivity", "Starting database test")
            
            // Create database helper
            val dbHelper = SkinDatabaseHelper(this)
            
            // Test 1: Insert a user
            val testUser = UserProfile(
                id = 0,
                fullName = "Database Test User",
                email = "dbtest${System.currentTimeMillis()}@example.com",
                phone = "9876543210",
                medicalHistory = listOf("Allergy", "Asthma"),
                otherHistory = "No other conditions"
            )
            
            val userId = dbHelper.insertUser(testUser, "test_password")
            Log.d("DatabaseTestActivity", "Inserted user with ID: $userId")
            
            if (userId == -1L) {
                Log.e("DatabaseTestActivity", "FAILED: Could not insert user")
                return
            }
            
            // Test 2: Retrieve the user
            val retrievedUser = dbHelper.getUserByEmail(testUser.email)
            if (retrievedUser == null) {
                Log.e("DatabaseTestActivity", "FAILED: Could not retrieve user after insertion")
                return
            }
            
            Log.d("DatabaseTestActivity", "Retrieved user: ${retrievedUser.fullName}, ID: ${retrievedUser.id}")
            
            // Test 3: Insert a prediction for the user
            val testPrediction = SkinPrediction(
                id = 0,
                imageUrl = "test_image_url",
                predictionResult = "Test prediction result",
                confidenceLevel = "High",
                timestamp = System.currentTimeMillis()
            )
            
            val predictionId = dbHelper.insertPrediction(userId.toInt(), testPrediction)
            Log.d("DatabaseTestActivity", "Inserted prediction with ID: $predictionId")
            
            if (predictionId == -1L) {
                Log.e("DatabaseTestActivity", "FAILED: Could not insert prediction")
                return
            }
            
            // Test 4: Retrieve predictions for the user
            val predictions = dbHelper.getAllPredictionsForUser(userId.toInt())
            Log.d("DatabaseTestActivity", "Retrieved ${predictions.size} predictions for user")
            
            if (predictions.isEmpty()) {
                Log.e("DatabaseTestActivity", "FAILED: No predictions found for user after insertion")
                return
            }
            
            // Test 5: Verify the prediction data
            val firstPrediction = predictions[0]
            if (firstPrediction.predictionResult != testPrediction.predictionResult) {
                Log.e("DatabaseTestActivity", "FAILED: Prediction data mismatch")
                return
            }
            
            // Keep database active
            dbHelper.keepDatabaseActive()
            
            Log.d("DatabaseTestActivity", "SUCCESS: All database operations completed successfully. User ID: $userId, Prediction ID: $predictionId")
            
            // Test 6: Get all users
            val allUsers = dbHelper.getAllUsers()
            Log.d("DatabaseTestActivity", "Total users in database: ${allUsers.size}")
            
            // Print all users
            allUsers.forEach { user ->
                Log.d("DatabaseTestActivity", "User: ID=${user.id}, Name=${user.fullName}, Email=${user.email}")
            }
            
        } catch (e: Exception) {
            Log.e("DatabaseTestActivity", "Error during database test", e)
        }
    }
}