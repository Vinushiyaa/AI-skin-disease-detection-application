package com.skure.app.scan

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skure.app.repository.ImageAnalysisRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val imageAnalysisRepository: ImageAnalysisRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ScanUiState())
    val uiState: StateFlow<ScanUiState> = _uiState.asStateFlow()
    
    fun analyzeImage(bitmap: Bitmap, apiKey: String) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        
        viewModelScope.launch {
            imageAnalysisRepository.analyzeImage(bitmap, apiKey)
                .onSuccess { result ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        analysisResult = result,
                        error = null
                    )
                }
                .onFailure { exception ->
                    val msg = exception.message ?: "Unknown error occurred"
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = msg,
                        analysisResult = null
                    )
                    if (msg.contains("Rate limited", ignoreCase = true)) {
                        startCooldown(seconds = 20)
                    }
                }
        }
    }
    
    fun clearResult() {
        _uiState.value = ScanUiState()
    }

    private fun startCooldown(seconds: Int) {
        viewModelScope.launch {
            var remaining = seconds
            while (remaining > 0) {
                _uiState.value = _uiState.value.copy(cooldownSeconds = remaining)
                delay(1000)
                remaining--
            }
            _uiState.value = _uiState.value.copy(cooldownSeconds = null, error = null)
        }
    }
}

data class ScanUiState(
    val isLoading: Boolean = false,
    val analysisResult: String? = null,
    val error: String? = null,
    val cooldownSeconds: Int? = null
)






