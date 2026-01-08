package com.skure.app.database

data class FoodRecommendation(
    val id: Long = 0,
    val userId: Int,
    val condition: String,
    val eat: List<String>,
    val avoid: List<String>,
    val substitutes: List<String>,
    val lifestyle: List<String>,
    val disclaimer: String?,
    val timestamp: Long = System.currentTimeMillis()
)