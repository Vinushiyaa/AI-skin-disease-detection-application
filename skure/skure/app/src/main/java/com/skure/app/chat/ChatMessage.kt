package com.skure.app.chat

data class ChatMessage(
    val id: Long = 0,
    val userId: Int = 0,
    val message: String = "",
    val isUserMessage: Boolean = true,
    val timestamp: Long = System.currentTimeMillis()
)