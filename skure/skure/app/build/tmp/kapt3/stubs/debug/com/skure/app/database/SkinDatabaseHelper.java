package com.skure.app.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 92\u00020\u0001:\u00019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eJ\"\u0010\u000f\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00110\u00102\u0006\u0010\t\u001a\u00020\bJ\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010\t\u001a\u00020\bJ\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0010J\"\u0010\u0018\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00110\u00102\u0006\u0010\t\u001a\u00020\bJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001a\u001a\u00020\u0012J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u0012J\u001e\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001fJ&\u0010 \u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0012J&\u0010$\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010%\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u0012J.\u0010\'\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u0012J\u0016\u0010,\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u0015J\u0016\u0010.\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u00020\u0012J\u0006\u00101\u001a\u00020\u0006J\u0010\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u0006H\u0016J \u00105\u001a\u0002032\u0006\u00104\u001a\u00020\u00062\u0006\u00106\u001a\u00020\b2\u0006\u00107\u001a\u00020\bH\u0016J\u0006\u00108\u001a\u000203R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006:"}, d2 = {"Lcom/skure/app/database/SkinDatabaseHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "activeDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "clearChatHistoryForUser", "", "userId", "clearConditionsForUser", "deleteAllPredictionsForUser", "deletePrediction", "predictionId", "", "getAllConditionsForUser", "", "", "", "", "getAllPredictionsForUser", "Lcom/skure/app/database/SkinPrediction;", "getAllUsers", "Lcom/skure/app/domain/UserProfile;", "getChatHistoryForUser", "getPasswordHashByEmail", "email", "getUserByEmail", "insertChatMessage", "message", "isUser", "", "insertConditionInfo", "conditionName", "description", "treatments", "insertFoodRecommendation", "foodName", "benefits", "insertHospitalInfo", "hospitalName", "address", "phone", "distance", "insertPrediction", "prediction", "insertUser", "user", "passwordHash", "keepDatabaseActive", "onCreate", "", "db", "onUpgrade", "oldVersion", "newVersion", "releaseDatabase", "Companion", "app_debug"})
public final class SkinDatabaseHelper extends android.database.sqlite.SQLiteOpenHelper {
    @org.jetbrains.annotations.Nullable()
    private android.database.sqlite.SQLiteDatabase activeDatabase;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DATABASE_NAME = "skure_app.db";
    private static final int DATABASE_VERSION = 2;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TABLE_USERS = "users";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_USER_ID = "user_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_FULL_NAME = "full_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_EMAIL = "email";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_PHONE = "phone";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_PASSWORD_HASH = "password_hash";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_REGISTRATION_DATE = "registration_date";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_MEDICAL_HISTORY = "medical_history";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_OTHER_HISTORY = "other_history";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TABLE_SKIN_PREDICTIONS = "skin_predictions";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_PREDICTION_ID = "prediction_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_IMAGE_URL = "image_url";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_PREDICTION_RESULT = "prediction_result";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_CONFIDENCE_LEVEL = "confidence_level";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_TIMESTAMP = "timestamp";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TABLE_CHATBOT_CONVERSATIONS = "chatbot_conversations";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_CONVERSATION_ID = "conversation_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_MESSAGE = "message";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_IS_USER = "is_user";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_CHAT_TIMESTAMP = "chat_timestamp";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TABLE_FOOD_RECOMMENDATIONS = "food_recommendations";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_FOOD_ID = "food_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_FOOD_NAME = "food_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_FOOD_DESCRIPTION = "food_description";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_NUTRITIONAL_BENEFITS = "nutritional_benefits";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_FOOD_TIMESTAMP = "food_timestamp";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TABLE_CONDITIONS = "conditions";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_CONDITION_ID = "condition_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_CONDITION_NAME = "condition_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_CONDITION_DESCRIPTION = "condition_description";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_TREATMENT_OPTIONS = "treatment_options";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_CONDITION_TIMESTAMP = "condition_timestamp";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TABLE_NEARBY_HOSPITALS = "nearby_hospitals";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_HOSPITAL_ID = "hospital_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_HOSPITAL_NAME = "hospital_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_HOSPITAL_ADDRESS = "hospital_address";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_HOSPITAL_PHONE = "hospital_phone";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_HOSPITAL_DISTANCE = "hospital_distance";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLUMN_HOSPITAL_TIMESTAMP = "hospital_timestamp";
    @org.jetbrains.annotations.NotNull()
    public static final com.skure.app.database.SkinDatabaseHelper.Companion Companion = null;
    
    public SkinDatabaseHelper(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null, null, null, 0);
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.NotNull()
    android.database.sqlite.SQLiteDatabase db) {
    }
    
    @java.lang.Override()
    public void onUpgrade(@org.jetbrains.annotations.NotNull()
    android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.database.sqlite.SQLiteDatabase keepDatabaseActive() {
        return null;
    }
    
    public final void releaseDatabase() {
    }
    
    public final long insertUser(@org.jetbrains.annotations.NotNull()
    com.skure.app.domain.UserProfile user, @org.jetbrains.annotations.NotNull()
    java.lang.String passwordHash) {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.skure.app.domain.UserProfile getUserByEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPasswordHashByEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skure.app.domain.UserProfile> getAllUsers() {
        return null;
    }
    
    public final long insertPrediction(int userId, @org.jetbrains.annotations.NotNull()
    com.skure.app.database.SkinPrediction prediction) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skure.app.database.SkinPrediction> getAllPredictionsForUser(int userId) {
        return null;
    }
    
    public final long insertChatMessage(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String message, boolean isUser) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getChatHistoryForUser(int userId) {
        return null;
    }
    
    public final int clearChatHistoryForUser(int userId) {
        return 0;
    }
    
    public final long insertFoodRecommendation(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String foodName, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String benefits) {
        return 0L;
    }
    
    public final long insertConditionInfo(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String conditionName, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String treatments) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getAllConditionsForUser(int userId) {
        return null;
    }
    
    public final int clearConditionsForUser(int userId) {
        return 0;
    }
    
    public final long insertHospitalInfo(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String hospitalName, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String distance) {
        return 0L;
    }
    
    public final int deletePrediction(long predictionId) {
        return 0;
    }
    
    public final int deleteAllPredictionsForUser(int userId) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\"\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\'X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/skure/app/database/SkinDatabaseHelper$Companion;", "", "()V", "COLUMN_CHAT_TIMESTAMP", "", "COLUMN_CONDITION_DESCRIPTION", "COLUMN_CONDITION_ID", "COLUMN_CONDITION_NAME", "COLUMN_CONDITION_TIMESTAMP", "COLUMN_CONFIDENCE_LEVEL", "COLUMN_CONVERSATION_ID", "COLUMN_EMAIL", "COLUMN_FOOD_DESCRIPTION", "COLUMN_FOOD_ID", "COLUMN_FOOD_NAME", "COLUMN_FOOD_TIMESTAMP", "COLUMN_FULL_NAME", "COLUMN_HOSPITAL_ADDRESS", "COLUMN_HOSPITAL_DISTANCE", "COLUMN_HOSPITAL_ID", "COLUMN_HOSPITAL_NAME", "COLUMN_HOSPITAL_PHONE", "COLUMN_HOSPITAL_TIMESTAMP", "COLUMN_IMAGE_URL", "COLUMN_IS_USER", "COLUMN_MEDICAL_HISTORY", "COLUMN_MESSAGE", "COLUMN_NUTRITIONAL_BENEFITS", "COLUMN_OTHER_HISTORY", "COLUMN_PASSWORD_HASH", "COLUMN_PHONE", "COLUMN_PREDICTION_ID", "COLUMN_PREDICTION_RESULT", "COLUMN_REGISTRATION_DATE", "COLUMN_TIMESTAMP", "COLUMN_TREATMENT_OPTIONS", "COLUMN_USER_ID", "DATABASE_NAME", "DATABASE_VERSION", "", "TABLE_CHATBOT_CONVERSATIONS", "TABLE_CONDITIONS", "TABLE_FOOD_RECOMMENDATIONS", "TABLE_NEARBY_HOSPITALS", "TABLE_SKIN_PREDICTIONS", "TABLE_USERS", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}