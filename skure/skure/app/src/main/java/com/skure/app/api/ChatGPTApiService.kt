package com.skure.app.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.Header
import retrofit2.http.POST

interface ChatGPTApiService {
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("v1/chat/completions")
    suspend fun analyzeImage(
        @Header("Authorization") authorization: String,
        @Body request: ChatGPTRequest
    ): Response<ChatGPTResponse>
}

data class ChatGPTRequest(
    val model: String = "openai/gpt-4o-mini",
    val messages: List<ChatGPTMessage>,
    val max_tokens: Int = 1000
)

data class ChatGPTMessage(
    val role: String,
    val content: List<ChatGPTContent>
)

data class ChatGPTContent(
    val type: String,
    val text: String? = null,
    val image_url: ChatGPTImageUrl? = null
)

data class ChatGPTImageUrl(
    val url: String
)

data class ChatGPTResponse(
    val choices: List<ChatGPTChoice>
)

data class ChatGPTChoice(
    val message: ChatGPTResponseMessage
)

data class ChatGPTResponseMessage(
    val content: String
)






