package com.skure.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skure.app.database.SkinDatabaseRepository
import com.skure.app.database.SkinPrediction
import com.skure.app.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AnalysisHistoryViewModel @Inject constructor(
    private val repository: SkinDatabaseRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    private var predictionsCache: List<SkinPrediction> = emptyList()

    fun getPredictions() = predictionsCache

    fun refreshPredictions() {
        viewModelScope.launch {
            val currentUser = authRepository.getCurrentUser()
            if (currentUser != null) {
                predictionsCache = repository.getAllPredictionsForUser(currentUser.id)
            } else {
                predictionsCache = emptyList()
            }
        }
    }

    fun deletePrediction(id: Long) {
        viewModelScope.launch {
            repository.deletePrediction(id)
            refreshPredictions()
        }
    }

    fun clearAllPredictions() {
        viewModelScope.launch {
            val currentUser = authRepository.getCurrentUser()
            if (currentUser != null) {
                repository.deleteAllPredictionsForUser(currentUser.id)
                predictionsCache = emptyList()
            }
        }
    }
    
    fun insertTestPrediction(prediction: SkinPrediction) {
        viewModelScope.launch {
            val currentUser = authRepository.getCurrentUser()
            if (currentUser != null) {
                repository.insertPrediction(currentUser.id, prediction)
                refreshPredictions()
            }
        }
    }
}

@Composable
fun AnalysisHistoryScreen(
    viewModel: AnalysisHistoryViewModel = hiltViewModel()
) {
    val predictions = viewModel.getPredictions()
    
    // Refresh predictions when the screen is first displayed
    LaunchedEffect(Unit) {
        viewModel.refreshPredictions()
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Test button for development - remove in production
        Button(
            onClick = {
                val testPrediction = SkinPrediction(
                    imageUrl = "test_image_url",
                    predictionResult = "Test prediction result for verification",
                    confidenceLevel = "Medium",
                    timestamp = System.currentTimeMillis()
                )
                viewModel.insertTestPrediction(testPrediction)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Insert Test Prediction")
        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Analysis History",
                style = MaterialTheme.typography.headlineMedium
            )
            
            if (predictions.isNotEmpty()) {
                Button(
                    onClick = { viewModel.clearAllPredictions() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Clear All")
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (predictions.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text(
                    text = "No analysis history yet",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn {
                items(predictions) { prediction ->
                    PredictionItem(
                        prediction = prediction,
                        onDelete = { viewModel.deletePrediction(prediction.id) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun PredictionItem(
    prediction: SkinPrediction,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
                        .format(Date(prediction.timestamp)),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Text(
                    text = prediction.confidenceLevel,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = prediction.predictionResult,
                style = MaterialTheme.typography.bodyMedium
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onDelete,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Delete")
                }
            }
        }
    }
}