package com.skure.app.database

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skure.app.domain.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseTestViewModel @Inject constructor(
    private val databaseRepository: SkinDatabaseRepository
) : ViewModel() {
    
    fun createTestUser(callback: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // Create a test user
                val testUser = UserProfile(
                    fullName = "Test User",
                    email = "test${System.currentTimeMillis()}@example.com",
                    phone = "1234567890",
                    medicalHistory = emptyList(),
                    otherHistory = ""
                )
                
                // Insert test user
                val userId = databaseRepository.insertUser(testUser, "test_password")
                Log.d("DatabaseTestViewModel", "Inserted user with ID: $userId")
                
                if (userId != -1L) {
                    // Create a test prediction
                    val prediction = SkinPrediction(
                        imageUrl = "test_image_url",
                        predictionResult = "Test prediction result",
                        confidenceLevel = "High",
                        timestamp = System.currentTimeMillis()
                    )
                    
                    // Insert test prediction
                    val predictionId = databaseRepository.insertPrediction(userId.toInt(), prediction)
                    Log.d("DatabaseTestViewModel", "Inserted prediction with ID: $predictionId for user ID: $userId")
                    
                    callback("Successfully created test user with ID: $userId and prediction with ID: $predictionId")
                } else {
                    callback("Failed to create test user")
                }
            } catch (e: Exception) {
                Log.e("DatabaseTestViewModel", "Error creating test user", e)
                callback("Error: ${e.message}")
            }
        }
    }
    
    fun createTestUserAndVerify(databaseHelper: SkinDatabaseHelper, callback: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // Create a test user
                val testUser = UserProfile(
                    fullName = "Test User",
                    email = "test${System.currentTimeMillis()}@example.com",
                    phone = "1234567890",
                    medicalHistory = emptyList(),
                    otherHistory = ""
                )
                
                // Insert test user directly using database helper
                val userId = databaseHelper.insertUser(testUser, "test_password")
                Log.d("DatabaseTestViewModel", "Inserted user with ID: $userId")
                
                if (userId != -1L) {
                    // Verify the user was inserted by getting all users
                    val users = databaseHelper.getAllUsers()
                    Log.d("DatabaseTestViewModel", "Found ${users.size} users in database")
                    
                    // Check if our user is in the list
                    val ourUser = users.find { it.email == testUser.email }
                    if (ourUser != null) {
                        callback("SUCCESS: Database is active and storing data correctly. User ID: ${ourUser.id}, Email: ${ourUser.email}")
                    } else {
                        callback("PARTIAL: User was inserted but not found in getAllUsers query")
                    }
                } else {
                    callback("FAILED: User was not inserted into database")
                }
            } catch (e: Exception) {
                Log.e("DatabaseTestViewModel", "Error testing database", e)
                callback("ERROR: ${e.message}")
            }
        }
    }
    
    fun getAllUsers(callback: (List<UserProfile>) -> Unit) {
        viewModelScope.launch {
            try {
                val users = databaseRepository.getAllUsers()
                callback(users)
            } catch (e: Exception) {
                Log.e("DatabaseTestViewModel", "Error getting users", e)
                callback(emptyList())
            }
        }
    }
    
    fun getAllPredictions(callback: (List<SkinPrediction>) -> Unit) {
        viewModelScope.launch {
            try {
                // This is a simplified approach - in a real app, you'd have a method to get all predictions
                callback(emptyList()) // Placeholder
            } catch (e: Exception) {
                Log.e("DatabaseTestViewModel", "Error getting predictions", e)
                callback(emptyList())
            }
        }
    }
}