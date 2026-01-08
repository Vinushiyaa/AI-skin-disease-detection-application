package com.skure.app.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.skure.app.database.SkinPrediction
import com.skure.app.domain.UserProfile
import java.io.File
import java.util.HashMap

class SkinDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // Keep a reference to the database to prevent it from being closed
    private var activeDatabase: SQLiteDatabase? = null
    
    companion object {
        private const val DATABASE_NAME = "skure_app.db"
        private const val DATABASE_VERSION = 2 // Incremented version to force table recreation
        
        // Users table
        private const val TABLE_USERS = "users"
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_FULL_NAME = "full_name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_PASSWORD_HASH = "password_hash"
        private const val COLUMN_REGISTRATION_DATE = "registration_date"
        private const val COLUMN_MEDICAL_HISTORY = "medical_history"
        private const val COLUMN_OTHER_HISTORY = "other_history"
        
        // Skin predictions table
        private const val TABLE_SKIN_PREDICTIONS = "skin_predictions"
        private const val COLUMN_PREDICTION_ID = "prediction_id"
        private const val COLUMN_IMAGE_URL = "image_url"
        private const val COLUMN_PREDICTION_RESULT = "prediction_result"
        private const val COLUMN_CONFIDENCE_LEVEL = "confidence_level"
        private const val COLUMN_TIMESTAMP = "timestamp"
        
        // Chatbot conversations table
        private const val TABLE_CHATBOT_CONVERSATIONS = "chatbot_conversations"
        private const val COLUMN_CONVERSATION_ID = "conversation_id"
        private const val COLUMN_MESSAGE = "message"
        private const val COLUMN_IS_USER = "is_user"
        private const val COLUMN_CHAT_TIMESTAMP = "chat_timestamp"
        
        // Food recommendations table
        private const val TABLE_FOOD_RECOMMENDATIONS = "food_recommendations"
        private const val COLUMN_FOOD_ID = "food_id"
        private const val COLUMN_FOOD_NAME = "food_name"
        private const val COLUMN_FOOD_DESCRIPTION = "food_description"
        private const val COLUMN_NUTRITIONAL_BENEFITS = "nutritional_benefits"
        private const val COLUMN_FOOD_TIMESTAMP = "food_timestamp"
        
        // Conditions information table
        private const val TABLE_CONDITIONS = "conditions"
        private const val COLUMN_CONDITION_ID = "condition_id"
        private const val COLUMN_CONDITION_NAME = "condition_name"
        private const val COLUMN_CONDITION_DESCRIPTION = "condition_description"
        private const val COLUMN_TREATMENT_OPTIONS = "treatment_options"
        private const val COLUMN_CONDITION_TIMESTAMP = "condition_timestamp"
        
        // Nearby hospitals table
        private const val TABLE_NEARBY_HOSPITALS = "nearby_hospitals"
        private const val COLUMN_HOSPITAL_ID = "hospital_id"
        private const val COLUMN_HOSPITAL_NAME = "hospital_name"
        private const val COLUMN_HOSPITAL_ADDRESS = "hospital_address"
        private const val COLUMN_HOSPITAL_PHONE = "hospital_phone"
        private const val COLUMN_HOSPITAL_DISTANCE = "hospital_distance"
        private const val COLUMN_HOSPITAL_TIMESTAMP = "hospital_timestamp"
    }
    
    override fun onCreate(db: SQLiteDatabase) {
        Log.d("SkinDatabaseHelper", "Creating database tables")
        
        // Create users table
        val createUsersTableQuery = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_FULL_NAME TEXT NOT NULL,
                $COLUMN_EMAIL TEXT NOT NULL UNIQUE,
                $COLUMN_PHONE TEXT NOT NULL,
                $COLUMN_PASSWORD_HASH TEXT NOT NULL,
                $COLUMN_REGISTRATION_DATE INTEGER NOT NULL,
                $COLUMN_MEDICAL_HISTORY TEXT,
                $COLUMN_OTHER_HISTORY TEXT
            )
        """.trimIndent()
        
        // Create skin predictions table with foreign key
        val createSkinPredictionsTableQuery = """
            CREATE TABLE $TABLE_SKIN_PREDICTIONS (
                $COLUMN_PREDICTION_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_ID INTEGER NOT NULL,
                $COLUMN_IMAGE_URL TEXT NOT NULL,
                $COLUMN_PREDICTION_RESULT TEXT NOT NULL,
                $COLUMN_CONFIDENCE_LEVEL TEXT NOT NULL,
                $COLUMN_TIMESTAMP INTEGER NOT NULL,
                FOREIGN KEY ($COLUMN_USER_ID) REFERENCES $TABLE_USERS ($COLUMN_USER_ID)
            )
        """.trimIndent()
        
        // Create chatbot conversations table with foreign key
        val createChatbotConversationsTableQuery = """
            CREATE TABLE $TABLE_CHATBOT_CONVERSATIONS (
                $COLUMN_CONVERSATION_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_ID INTEGER NOT NULL,
                $COLUMN_MESSAGE TEXT NOT NULL,
                $COLUMN_IS_USER INTEGER NOT NULL,
                $COLUMN_CHAT_TIMESTAMP INTEGER NOT NULL,
                FOREIGN KEY ($COLUMN_USER_ID) REFERENCES $TABLE_USERS ($COLUMN_USER_ID)
            )
        """.trimIndent()
        
        // Create food recommendations table with foreign key
        val createFoodRecommendationsTableQuery = """
            CREATE TABLE $TABLE_FOOD_RECOMMENDATIONS (
                $COLUMN_FOOD_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_ID INTEGER NOT NULL,
                $COLUMN_FOOD_NAME TEXT NOT NULL,
                $COLUMN_FOOD_DESCRIPTION TEXT NOT NULL,
                $COLUMN_NUTRITIONAL_BENEFITS TEXT NOT NULL,
                $COLUMN_FOOD_TIMESTAMP INTEGER NOT NULL,
                FOREIGN KEY ($COLUMN_USER_ID) REFERENCES $TABLE_USERS ($COLUMN_USER_ID)
            )
        """.trimIndent()
        
        // Create conditions information table
        val createConditionsTableQuery = """
            CREATE TABLE $TABLE_CONDITIONS (
                $COLUMN_CONDITION_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_ID INTEGER NOT NULL,
                $COLUMN_CONDITION_NAME TEXT NOT NULL,
                $COLUMN_CONDITION_DESCRIPTION TEXT NOT NULL,
                $COLUMN_TREATMENT_OPTIONS TEXT NOT NULL,
                $COLUMN_CONDITION_TIMESTAMP INTEGER NOT NULL,
                FOREIGN KEY ($COLUMN_USER_ID) REFERENCES $TABLE_USERS ($COLUMN_USER_ID)
            )
        """.trimIndent()
        
        // Create nearby hospitals table with foreign key
        val createNearbyHospitalsTableQuery = """
            CREATE TABLE $TABLE_NEARBY_HOSPITALS (
                $COLUMN_HOSPITAL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_ID INTEGER NOT NULL,
                $COLUMN_HOSPITAL_NAME TEXT NOT NULL,
                $COLUMN_HOSPITAL_ADDRESS TEXT NOT NULL,
                $COLUMN_HOSPITAL_PHONE TEXT NOT NULL,
                $COLUMN_HOSPITAL_DISTANCE TEXT NOT NULL,
                $COLUMN_HOSPITAL_TIMESTAMP INTEGER NOT NULL,
                FOREIGN KEY ($COLUMN_USER_ID) REFERENCES $TABLE_USERS ($COLUMN_USER_ID)
            )
        """.trimIndent()
        
        db.execSQL(createUsersTableQuery)
        db.execSQL(createSkinPredictionsTableQuery)
        db.execSQL(createChatbotConversationsTableQuery)
        db.execSQL(createFoodRecommendationsTableQuery)
        db.execSQL(createConditionsTableQuery)
        db.execSQL(createNearbyHospitalsTableQuery)
        
        Log.d("SkinDatabaseHelper", "Database tables created successfully")
    }
    
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d("SkinDatabaseHelper", "Upgrading database from version $oldVersion to $newVersion")
        
        // Drop existing tables if they exist
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NEARBY_HOSPITALS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CONDITIONS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FOOD_RECOMMENDATIONS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CHATBOT_CONVERSATIONS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SKIN_PREDICTIONS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        
        // Create tables again
        onCreate(db)
    }
    
    // Method to keep database active
    fun keepDatabaseActive(): SQLiteDatabase {
        if (activeDatabase == null || !activeDatabase!!.isOpen) {
            activeDatabase = writableDatabase
            Log.d("SkinDatabaseHelper", "Database activated")
        }
        return activeDatabase!!
    }
    
    // Method to close database when no longer needed
    fun releaseDatabase() {
        activeDatabase?.close()
        activeDatabase = null
        Log.d("SkinDatabaseHelper", "Database released")
    }
    
    // User operations
    fun insertUser(user: UserProfile, passwordHash: String): Long {
        val db = keepDatabaseActive()
        val values = ContentValues().apply {
            put(COLUMN_FULL_NAME, user.fullName)
            put(COLUMN_EMAIL, user.email)
            put(COLUMN_PHONE, user.phone)
            put(COLUMN_PASSWORD_HASH, passwordHash)
            put(COLUMN_REGISTRATION_DATE, System.currentTimeMillis())
            put(COLUMN_MEDICAL_HISTORY, user.medicalHistory.joinToString(","))
            put(COLUMN_OTHER_HISTORY, user.otherHistory)
        }
        
        val id = db.insert(TABLE_USERS, null, values)
        Log.d("SkinDatabaseHelper", "Inserted user with ID: $id")
        return id
    }
    
    fun getUserByEmail(email: String): UserProfile? {
        val db = keepDatabaseActive()
        val cursor = db.query(
            TABLE_USERS,
            arrayOf(COLUMN_USER_ID, COLUMN_FULL_NAME, COLUMN_EMAIL, COLUMN_PHONE, 
                   COLUMN_PASSWORD_HASH, COLUMN_REGISTRATION_DATE, COLUMN_MEDICAL_HISTORY, COLUMN_OTHER_HISTORY),
            "$COLUMN_EMAIL = ?",
            arrayOf(email),
            null, null, null
        )
        
        var user: UserProfile? = null
        with(cursor) {
            if (moveToFirst()) {
                val id = getInt(getColumnIndex(COLUMN_USER_ID))
                val fullName = getString(getColumnIndex(COLUMN_FULL_NAME))
                val userEmail = getString(getColumnIndex(COLUMN_EMAIL))
                val phone = getString(getColumnIndex(COLUMN_PHONE))
                val passwordHash = getString(getColumnIndex(COLUMN_PASSWORD_HASH))
                val registrationDate = getLong(getColumnIndex(COLUMN_REGISTRATION_DATE))
                val medicalHistoryStr = getString(getColumnIndex(COLUMN_MEDICAL_HISTORY))
                val otherHistory = getString(getColumnIndex(COLUMN_OTHER_HISTORY))
                
                val medicalHistory = if (!medicalHistoryStr.isNullOrEmpty()) {
                    medicalHistoryStr.split(",").toList()
                } else {
                    emptyList()
                }
                
                user = UserProfile(
                    id = id,
                    fullName = fullName,
                    email = userEmail,
                    phone = phone,
                    medicalHistory = medicalHistory,
                    otherHistory = otherHistory ?: ""
                )
            }
        }
        
        cursor.close()
        Log.d("SkinDatabaseHelper", "Retrieved user by email: ${user?.email}")
        return user
    }
    
    fun getPasswordHashByEmail(email: String): String? {
        val db = keepDatabaseActive()
        val cursor = db.query(
            TABLE_USERS,
            arrayOf(COLUMN_PASSWORD_HASH),
            "$COLUMN_EMAIL = ?",
            arrayOf(email),
            null,
            null,
            null
        )

        var passwordHash: String? = null
        if (cursor.moveToFirst()) {
            passwordHash = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD_HASH))
        }

        cursor.close()
        Log.d("SkinDatabaseHelper", "Retrieved password hash for email: $email -> ${passwordHash != null}")
        return passwordHash
    }
    
    fun getAllUsers(): List<UserProfile> {
        val users = mutableListOf<UserProfile>()
        val db = keepDatabaseActive()
        val cursor = db.query(TABLE_USERS, null, null, null, null, null, null)
        
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndex(COLUMN_USER_ID))
                val fullName = getString(getColumnIndex(COLUMN_FULL_NAME))
                val email = getString(getColumnIndex(COLUMN_EMAIL))
                val phone = getString(getColumnIndex(COLUMN_PHONE))
                val passwordHash = getString(getColumnIndex(COLUMN_PASSWORD_HASH))
                val registrationDate = getLong(getColumnIndex(COLUMN_REGISTRATION_DATE))
                val medicalHistoryStr = getString(getColumnIndex(COLUMN_MEDICAL_HISTORY))
                val otherHistory = getString(getColumnIndex(COLUMN_OTHER_HISTORY))
                
                val medicalHistory = if (!medicalHistoryStr.isNullOrEmpty()) {
                    medicalHistoryStr.split(",").toList()
                } else {
                    emptyList()
                }
                
                users.add(
                    UserProfile(
                        id = id,
                        fullName = fullName,
                        email = email,
                        phone = phone,
                        medicalHistory = medicalHistory,
                        otherHistory = otherHistory ?: ""
                    )
                )
            }
        }
        
        cursor.close()
        Log.d("SkinDatabaseHelper", "Retrieved ${users.size} users")
        return users
    }
    
    // Skin prediction operations with user ID
    fun insertPrediction(userId: Int, prediction: SkinPrediction): Long {
        val db = keepDatabaseActive()
        val values = ContentValues().apply {
            put(COLUMN_USER_ID, userId)
            put(COLUMN_IMAGE_URL, prediction.imageUrl)
            put(COLUMN_PREDICTION_RESULT, prediction.predictionResult)
            put(COLUMN_CONFIDENCE_LEVEL, prediction.confidenceLevel)
            put(COLUMN_TIMESTAMP, prediction.timestamp)
        }
        
        val id = db.insert(TABLE_SKIN_PREDICTIONS, null, values)
        Log.d("SkinDatabaseHelper", "Inserted prediction with ID: $id")
        return id
    }
    
    fun getAllPredictionsForUser(userId: Int): List<SkinPrediction> {
        val predictions = mutableListOf<SkinPrediction>()
        val db = keepDatabaseActive()
        val cursor = db.query(
            TABLE_SKIN_PREDICTIONS,
            arrayOf(COLUMN_PREDICTION_ID, COLUMN_IMAGE_URL, COLUMN_PREDICTION_RESULT, COLUMN_CONFIDENCE_LEVEL, COLUMN_TIMESTAMP),
            "$COLUMN_USER_ID = ?",
            arrayOf(userId.toString()),
            null, null,
            "$COLUMN_TIMESTAMP DESC"
        )
        
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndex(COLUMN_PREDICTION_ID))
                val imageUrl = getString(getColumnIndex(COLUMN_IMAGE_URL))
                val predictionResult = getString(getColumnIndex(COLUMN_PREDICTION_RESULT))
                val confidenceLevel = getString(getColumnIndex(COLUMN_CONFIDENCE_LEVEL))
                val timestamp = getLong(getColumnIndex(COLUMN_TIMESTAMP))
                
                predictions.add(
                    SkinPrediction(
                        id = id,
                        imageUrl = imageUrl,
                        predictionResult = predictionResult,
                        confidenceLevel = confidenceLevel,
                        timestamp = timestamp
                    )
                )
            }
        }
        
        cursor.close()
        Log.d("SkinDatabaseHelper", "Retrieved ${predictions.size} predictions for user ID: $userId")
        return predictions
    }
    
    // Chatbot conversation operations
    fun insertChatMessage(userId: Int, message: String, isUser: Boolean): Long {
        val db = keepDatabaseActive()
        val values = ContentValues().apply {
            put(COLUMN_USER_ID, userId)
            put(COLUMN_MESSAGE, message)
            put(COLUMN_IS_USER, if (isUser) 1 else 0)
            put(COLUMN_CHAT_TIMESTAMP, System.currentTimeMillis())
        }
        
        val id = db.insert(TABLE_CHATBOT_CONVERSATIONS, null, values)
        Log.d("SkinDatabaseHelper", "Inserted chat message with ID: $id")
        return id
    }
    
    fun getChatHistoryForUser(userId: Int): List<Map<String, Any?>> {
        val chatHistory = mutableListOf<Map<String, Any?>>()
        val db = keepDatabaseActive()
        val cursor = db.query(
            TABLE_CHATBOT_CONVERSATIONS,
            arrayOf(COLUMN_CONVERSATION_ID, COLUMN_MESSAGE, COLUMN_IS_USER, COLUMN_CHAT_TIMESTAMP),
            "$COLUMN_USER_ID = ?",
            arrayOf(userId.toString()),
            null, null,
            "$COLUMN_CHAT_TIMESTAMP ASC"
        )
        
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndex(COLUMN_CONVERSATION_ID))
                val message = getString(getColumnIndex(COLUMN_MESSAGE))
                val isUser = getInt(getColumnIndex(COLUMN_IS_USER)) == 1
                val timestamp = getLong(getColumnIndex(COLUMN_CHAT_TIMESTAMP))
                
                chatHistory.add(
                    mapOf(
                        "id" to id,
                        "message" to message,
                        "isUser" to isUser,
                        "timestamp" to timestamp
                    )
                )
            }
        }
        
        cursor.close()
        Log.d("SkinDatabaseHelper", "Retrieved ${chatHistory.size} chat messages for user ID: $userId")
        return chatHistory
    }
    
    fun clearChatHistoryForUser(userId: Int): Int {
        val db = keepDatabaseActive()
        val result = db.delete(
            TABLE_CHATBOT_CONVERSATIONS,
            "$COLUMN_USER_ID = ?",
            arrayOf(userId.toString())
        )
        Log.d("SkinDatabaseHelper", "Cleared $result chat messages for user ID: $userId")
        return result
    }
    
    // Food recommendation operations
    fun insertFoodRecommendation(userId: Int, foodName: String, description: String, benefits: String): Long {
        val db = keepDatabaseActive()
        val values = ContentValues().apply {
            put(COLUMN_USER_ID, userId)
            put(COLUMN_FOOD_NAME, foodName)
            put(COLUMN_FOOD_DESCRIPTION, description)
            put(COLUMN_NUTRITIONAL_BENEFITS, benefits)
            put(COLUMN_FOOD_TIMESTAMP, System.currentTimeMillis())
        }
        
        val id = db.insert(TABLE_FOOD_RECOMMENDATIONS, null, values)
        Log.d("SkinDatabaseHelper", "Inserted food recommendation with ID: $id")
        return id
    }
    
    // Condition information operations
    fun insertConditionInfo(userId: Int, conditionName: String, description: String, treatments: String): Long {
        val db = keepDatabaseActive()
        val values = ContentValues().apply {
            put(COLUMN_USER_ID, userId)
            put(COLUMN_CONDITION_NAME, conditionName)
            put(COLUMN_CONDITION_DESCRIPTION, description)
            put(COLUMN_TREATMENT_OPTIONS, treatments)
            put(COLUMN_CONDITION_TIMESTAMP, System.currentTimeMillis())
        }
        
        val id = db.insert(TABLE_CONDITIONS, null, values)
        Log.d("SkinDatabaseHelper", "Inserted condition info with ID: $id")
        return id
    }
    
    fun getAllConditionsForUser(userId: Int): List<Map<String, Any?>> {
        val conditions = mutableListOf<Map<String, Any?>>()
        val db = keepDatabaseActive()
        val cursor = db.query(
            TABLE_CONDITIONS,
            arrayOf(COLUMN_CONDITION_ID, COLUMN_CONDITION_NAME, COLUMN_CONDITION_DESCRIPTION, COLUMN_TREATMENT_OPTIONS, COLUMN_CONDITION_TIMESTAMP),
            "$COLUMN_USER_ID = ?",
            arrayOf(userId.toString()),
            null, null,
            "$COLUMN_CONDITION_TIMESTAMP DESC"
        )
        
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndex(COLUMN_CONDITION_ID))
                val name = getString(getColumnIndex(COLUMN_CONDITION_NAME))
                val description = getString(getColumnIndex(COLUMN_CONDITION_DESCRIPTION))
                val treatments = getString(getColumnIndex(COLUMN_TREATMENT_OPTIONS))
                val timestamp = getLong(getColumnIndex(COLUMN_CONDITION_TIMESTAMP))
                
                conditions.add(
                    mapOf(
                        "id" to id,
                        "name" to name,
                        "description" to description,
                        "treatments" to treatments,
                        "timestamp" to timestamp
                    )
                )
            }
        }
        
        cursor.close()
        Log.d("SkinDatabaseHelper", "Retrieved ${conditions.size} conditions for user ID: $userId")
        return conditions
    }
    
    fun clearConditionsForUser(userId: Int): Int {
        val db = keepDatabaseActive()
        val result = db.delete(
            TABLE_CONDITIONS,
            "$COLUMN_USER_ID = ?",
            arrayOf(userId.toString())
        )
        Log.d("SkinDatabaseHelper", "Cleared $result conditions for user ID: $userId")
        return result
    }
    
    // Nearby hospital operations
    fun insertHospitalInfo(userId: Int, hospitalName: String, address: String, phone: String, distance: String): Long {
        val db = keepDatabaseActive()
        val values = ContentValues().apply {
            put(COLUMN_USER_ID, userId)
            put(COLUMN_HOSPITAL_NAME, hospitalName)
            put(COLUMN_HOSPITAL_ADDRESS, address)
            put(COLUMN_HOSPITAL_PHONE, phone)
            put(COLUMN_HOSPITAL_DISTANCE, distance)
            put(COLUMN_HOSPITAL_TIMESTAMP, System.currentTimeMillis())
        }
        
        val id = db.insert(TABLE_NEARBY_HOSPITALS, null, values)
        Log.d("SkinDatabaseHelper", "Inserted hospital info with ID: $id")
        return id
    }
    
    // Delete operations
    fun deletePrediction(predictionId: Long): Int {
        val db = keepDatabaseActive()
        val result = db.delete(
            TABLE_SKIN_PREDICTIONS,
            "$COLUMN_PREDICTION_ID = ?",
            arrayOf(predictionId.toString())
        )
        Log.d("SkinDatabaseHelper", "Deleted $result prediction(s) with ID: $predictionId")
        return result
    }
    
    fun deleteAllPredictionsForUser(userId: Int): Int {
        val db = keepDatabaseActive()
        val result = db.delete(
            TABLE_SKIN_PREDICTIONS,
            "$COLUMN_USER_ID = ?",
            arrayOf(userId.toString())
        )
        Log.d("SkinDatabaseHelper", "Deleted all $result prediction(s) for user ID: $userId")
        return result
    }
}