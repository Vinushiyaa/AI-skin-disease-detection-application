package com.skure.app.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.skure.app.domain.UserProfile
import com.skure.app.database.SkinDatabaseRepository
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val databaseRepository: SkinDatabaseRepository
) {
    private val prefs: SharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    
    suspend fun register(
        fullName: String,
        email: String,
        phone: String,
        password: String
    ): Result<UserProfile> {
        return try {
            Log.d("AuthRepository", "Attempting to register user: $email")
            
            // Check if user already exists in database
            val existingUser = databaseRepository.getUserByEmail(email)
            if (existingUser != null) {
                Log.d("AuthRepository", "Email already registered: $email")
                return Result.failure(Exception("Email already registered"))
            }
            
            val profile = UserProfile(
                id = 0, // Will be set by database
                fullName = fullName,
                email = email,
                phone = phone,
                medicalHistory = emptyList(),
                otherHistory = ""
            )
            
            // Insert user into database
            val userId = databaseRepository.insertUser(profile, password)
            Log.d("AuthRepository", "Inserted user with ID: $userId")
            
            if (userId == -1L) {
                Log.e("AuthRepository", "Failed to register user: $email")
                return Result.failure(Exception("Failed to register user"))
            }
            
            // Update profile with database ID
            val registeredProfile = profile.copy(id = userId.toInt())
            
            // Store current user in preferences
            prefs.edit()
                .putString("current_user", email)
                .apply()
            
            Log.d("AuthRepository", "Successfully registered user: $email with ID: ${registeredProfile.id}")
            return Result.success(registeredProfile)
        } catch (e: Exception) {
            Log.e("AuthRepository", "Error during registration for $email", e)
            return Result.failure(e)
        }
    }
    
    suspend fun login(email: String, password: String): Result<UserProfile> {
        return try {
            Log.d("AuthRepository", "Attempting to login user: $email")
            
            // Get user from database
            val profile = databaseRepository.getUserByEmail(email)
            if (profile == null) {
                Log.d("AuthRepository", "Email not registered: $email")
                return Result.failure(Exception("Email not registered"))
            }
            
            val storedPasswordHash = databaseRepository.getPasswordHashByEmail(email)
            if (storedPasswordHash.isNullOrEmpty()) {
                Log.d("AuthRepository", "Password not found for: $email")
                return Result.failure(Exception("Invalid email or password"))
            }
            
            if (storedPasswordHash != password) {
                Log.d("AuthRepository", "Invalid password for: $email")
                return Result.failure(Exception("Invalid email or password"))
            }
            
            prefs.edit().putString("current_user", email).apply()
            Log.d("AuthRepository", "Successfully logged in user: $email")
            
            return Result.success(profile)
        } catch (e: Exception) {
            Log.e("AuthRepository", "Error during login for $email", e)
            return Result.failure(Exception("Invalid email or password"))
        }
    }
    
    suspend fun updateProfile(profile: UserProfile, password: String): Result<UserProfile> {
        return try {
            // Update user in database
            // Note: This is a simplified implementation
            // In a real app, you would have a separate update method
            return Result.success(profile)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
    
    suspend fun getCurrentUser(): UserProfile? {
        val email = prefs.getString("current_user", null) ?: return null
        return databaseRepository.getUserByEmail(email)
    }
    
    fun logout() {
        prefs.edit().remove("current_user").apply()
    }
}