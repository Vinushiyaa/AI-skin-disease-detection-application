package com.skure.app.database

data class ChatMessage(
    val id: Long = 0,
    val userId: Int,
    val message: String,
    val isUserMessage: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)