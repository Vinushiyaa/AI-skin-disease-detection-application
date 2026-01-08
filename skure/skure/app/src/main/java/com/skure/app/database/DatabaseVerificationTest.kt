package com.skure.app.database

import android.content.Context
import android.util.Log

class DatabaseVerificationTest(private val context: Context) {
    
    fun verifyDatabase(): String {
        return try {
            val dbHelper = SkinDatabaseHelper(context)
            
            // Create a test user
            val testUser = com.skure.app.domain.UserProfile(
                fullName = "Verification Test User",
                email = "verify${System.currentTimeMillis()}@test.com",
                phone = "9876543210",
                medicalHistory = emptyList(),
                otherHistory = ""
            )
            
            // Insert test user
            val userId = dbHelper.insertUser(testUser, "verify_password")
            Log.d("DatabaseVerification", "Inserted verification user with ID: $userId")
            
            if (userId != -1L) {
                // Get all users to verify the insertion
                val users = dbHelper.getAllUsers()
                Log.d("DatabaseVerification", "Found ${users.size} users in database")
                
                // Check if our user is in the list
                val ourUser = users.find { it.email == testUser.email }
                if (ourUser != null) {
                    // Keep database active
                    dbHelper.keepDatabaseActive()
                    return "SUCCESS: Database is active and storing data correctly. User ID: ${ourUser.id}, Email: ${ourUser.email}"
                } else {
                    // Keep database active
                    dbHelper.keepDatabaseActive()
                    return "PARTIAL: User was inserted but not found in getAllUsers query"
                }
            } else {
                // Keep database active
                dbHelper.keepDatabaseActive()
                return "FAILED: User was not inserted into database"
            }
        } catch (e: Exception) {
            Log.e("DatabaseVerification", "Error verifying database", e)
            return "ERROR: ${e.message}"
        }
    }
    
    fun closeDatabase() {
        try {
            val dbHelper = SkinDatabaseHelper(context)
            dbHelper.releaseDatabase()
            Log.d("DatabaseVerification", "Database connection closed")
        } catch (e: Exception) {
            Log.e("DatabaseVerification", "Error closing database", e)
        }
    }
    
    // Method to keep database active periodically
    fun startPeriodicKeepAlive() {
        Thread {
            while (true) {
                try {
                    val dbHelper = SkinDatabaseHelper(context)
                    dbHelper.keepDatabaseActive()
                    Log.d("DatabaseVerification", "Database keep-alive ping")
                    Thread.sleep(30000) // Keep alive every 30 seconds
                } catch (e: Exception) {
                    Log.e("DatabaseVerification", "Error in keep-alive", e)
                }
            }
        }.start()
    }
}