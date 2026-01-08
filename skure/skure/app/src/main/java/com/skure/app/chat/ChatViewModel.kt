package com.skure.app.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skure.app.repository.TextChatRepository
import com.skure.app.repository.AuthRepository
import com.skure.app.database.SkinDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UiChatMessage(val role: String, val text: String)

data class ChatUi(val messages: List<UiChatMessage> = emptyList(), val loading: Boolean = false, val error: String? = null)

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: TextChatRepository,
    private val authRepository: AuthRepository,
    private val databaseRepository: SkinDatabaseRepository
) : ViewModel() {
    private val _ui = MutableStateFlow(ChatUi())
    val ui: StateFlow<ChatUi> = _ui

    fun loadChatHistory() {
        viewModelScope.launch {
            try {
                val currentUser = authRepository.getCurrentUser()
                if (currentUser != null) {
                    val chatHistory = databaseRepository.getChatHistoryForUser(currentUser.id)
                    val messages = chatHistory.map { chatMap ->
                        val isUser = chatMap["isUser"] as Boolean
                        val message = chatMap["message"] as String
                        UiChatMessage(
                            role = if (isUser) "user" else "assistant",
                            text = message
                        )
                    }
                    
                    _ui.value = _ui.value.copy(messages = messages)
                }
            } catch (e: Exception) {
                // Handle error silently or log it
            }
        }
    }

    fun send(text: String, apiKey: String) {
        if (text.isBlank()) return
        val current = _ui.value.messages + UiChatMessage("user", text)
        _ui.value = _ui.value.copy(messages = current, loading = true, error = null)
        
        // Save user message to database
        viewModelScope.launch {
            val currentUser = authRepository.getCurrentUser()
            if (currentUser != null) {
                databaseRepository.insertChatMessage(currentUser.id, text, true)
            }
        }
        
        viewModelScope.launch {
            val history = current.map { it.role to it.text }
            val result = repository.askMedicalQuestion(history, apiKey)
            result.onSuccess { reply ->
                _ui.value = _ui.value.copy(
                    messages = _ui.value.messages + UiChatMessage("assistant", reply),
                    loading = false,
                    error = null
                )
                
                // Save assistant message to database
                val currentUser = authRepository.getCurrentUser()
                if (currentUser != null) {
                    databaseRepository.insertChatMessage(currentUser.id, reply, false)
                }
            }.onFailure { e ->
                _ui.value = _ui.value.copy(loading = false, error = e.message)
            }
        }
    }
    
    fun clearChatHistory() {
        viewModelScope.launch {
            val currentUser = authRepository.getCurrentUser()
            if (currentUser != null) {
                databaseRepository.clearChatHistoryForUser(currentUser.id)
                _ui.value = _ui.value.copy(messages = emptyList())
            }
        }
    }
}