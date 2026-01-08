package com.skure.app.database

data class SkinPrediction(
    val id: Long = 0,
    val imageUrl: String,
    val predictionResult: String,
    val confidenceLevel: String,
    val timestamp: Long = System.currentTimeMillis()
)