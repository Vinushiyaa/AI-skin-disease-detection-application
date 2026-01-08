package com.skure.app.database

import android.content.Context
import android.util.Log
import com.skure.app.domain.UserProfile
import java.io.File
import java.util.Timer
import java.util.TimerTask

class DatabaseInitializer(private val context: Context) {
    private var dbHelper: SkinDatabaseHelper? = null
    private var timer: Timer? = null
    
    fun initializeDatabase() {
        try {
            // First, let's ensure the database is created by getting a reference to it
            dbHelper = SkinDatabaseHelper(context)
            
            // Force database creation by getting a writable database
            val db = dbHelper?.writableDatabase
            Log.d("DatabaseInit", "Database path: ${context.getDatabasePath("skure_app.db").absolutePath}")
            Log.d("DatabaseInit", "Database exists: ${context.getDatabasePath("skure_app.db").exists()}")
            // Note: We're not closing the database here to keep it open for inspection
            
            // Create a test user
            val testUser = UserProfile(
                fullName = "Test User",
                email = "test@example.com",
                phone = "1234567890",
                medicalHistory = emptyList(),
                otherHistory = ""
            )
            
            Log.d("DatabaseInit", "Attempting to insert user: ${testUser.email}")
            
            // Insert test user
            val userId = dbHelper?.insertUser(testUser, "test_password")
            Log.d("DatabaseInit", "Inserted user with ID: $userId")
            
            // Create a test prediction
            if (userId != null && userId != -1L) {
                val prediction = SkinPrediction(
                    imageUrl = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAEBAQEBAQECAQECAQEBAQIFBQYCAgYFBQYKCggIBwgICAgKDhcLDAsKDRcUEhMUExwaGx8fGhcfIiIeJSUlHyMjJyQjIyMjIyP/2wBDAQEB",
                    predictionResult = "Eczema",
                    confidenceLevel = "High",
                    timestamp = System.currentTimeMillis()
                )
                
                Log.d("DatabaseInit", "Attempting to insert prediction for user ID: $userId")
                val predictionId = dbHelper?.insertPrediction(userId.toInt(), prediction)
                Log.d("DatabaseInit", "Inserted prediction with ID: $predictionId for user ID: $userId")
            }
            
            // Start periodic queries to keep database active
            startPeriodicQueries()
            
            Log.d("DatabaseInit", "Database initialization completed successfully")
        } catch (e: Exception) {
            Log.e("DatabaseInit", "Error initializing database", e)
        }
    }
    
    private fun startPeriodicQueries() {
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                try {
                    // Perform a simple query to keep database active
                    val predictions = dbHelper?.getAllPredictionsForUser(1) ?: emptyList()
                    Log.d("DatabaseKeepAlive", "Keep alive query - Found ${predictions.size} predictions")
                } catch (e: Exception) {
                    Log.e("DatabaseKeepAlive", "Error in keep alive query", e)
                }
            }
        }, 5000, 5000) // Query every 5 seconds
    }

    fun queryDatabase(): List<SkinPrediction> {
        try {
            // Get our test user
            val user = dbHelper?.getUserByEmail("test@example.com")
            
            if (user != null) {
                Log.d("DatabaseQuery", "Found user - ID: ${user.id}, Name: ${user.fullName}, Email: ${user.email}")
                // Get predictions for this user
                val predictions = dbHelper?.getAllPredictionsForUser(user.id)
                Log.d("DatabaseQuery", "Found ${predictions?.size} predictions for user ID: ${user.id}")
                return predictions ?: emptyList()
            } else {
                Log.d("DatabaseQuery", "No user found with email test@example.com")
                return emptyList()
            }
        } catch (e: Exception) {
            Log.e("DatabaseQuery", "Error querying database", e)
            return emptyList()
        }
    }
    
    fun queryAllUsers(): List<UserProfile> {
        try {
            // This is a simplified approach - in a real app, you'd have a method to get all users
            // For now, we'll just try to get our test user
            val user = dbHelper?.getUserByEmail("test@example.com")
            
            if (user != null) {
                Log.d("DatabaseQuery", "Found user - ID: ${user.id}, Name: ${user.fullName}, Email: ${user.email}")
                return listOf(user)
            } else {
                Log.d("DatabaseQuery", "No user found with email test@example.com")
                return emptyList()
            }
        } catch (e: Exception) {
            Log.e("DatabaseQuery", "Error querying users", e)
            return emptyList()
        }
    }
    
    fun stopPeriodicQueries() {
        timer?.cancel()
        timer = null
    }
    
    fun closeDatabase() {
        stopPeriodicQueries()
        dbHelper?.close()
        dbHelper = null
    }
}