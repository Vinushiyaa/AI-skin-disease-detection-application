package com.skure.app.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skure.app.database.SkinDatabaseRepository
import com.skure.app.database.SkinPrediction
import com.skure.app.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DatabaseDebugViewModel @Inject constructor(
    private val repository: SkinDatabaseRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    private var predictions: List<SkinPrediction> = emptyList()
    
    fun getPredictions() = predictions
    
    fun loadPredictions() {
        viewModelScope.launch {
            try {
                val currentUser = authRepository.getCurrentUser()
                if (currentUser != null) {
                    predictions = repository.getAllPredictionsForUser(currentUser.id)
                    Log.d("DatabaseDebug", "Loaded ${predictions.size} predictions from database for user ID: ${currentUser.id}")
                } else {
                    predictions = emptyList()
                    Log.d("DatabaseDebug", "No current user, loaded 0 predictions")
                }
            } catch (e: Exception) {
                Log.e("DatabaseDebug", "Error loading predictions", e)
            }
        }
    }
    
    fun addSampleData() {
        viewModelScope.launch {
            try {
                val currentUser = authRepository.getCurrentUser()
                if (currentUser != null) {
                    // Add a sample prediction
                    val samplePrediction = SkinPrediction(
                        imageUrl = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAEBAQEBAQECAQECAQEBAQIFBQYCAgYFBQYKCggIBwgICAgKDhcLDAsKDRcUEhMUExwaGx8fGhcfIiIeJSUlHyMjJyQjIyMjIyP/2wBDAQEBAQEBAQIBAQICAgECAgMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwP/wAARCAABAAEDASIAAhEBAxEB/8QAFQABAQAAAAAAAAAAAAAAAAAAAAv/xAAUEAEAAAAAAAAAAAAAAAAAAAAA/8QAFQEBAQAAAAAAAAAAAAAAAAAAAAX/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwCdABmX/9k=",
                        predictionResult = "Sample analysis result for testing database functionality. This shows that the database is working correctly.",
                        confidenceLevel = "Medium",
                        timestamp = System.currentTimeMillis()
                    )
                    
                    val id = repository.insertPrediction(currentUser.id, samplePrediction)
                    Log.d("DatabaseDebug", "Added sample prediction with ID: $id")
                    
                    // Reload predictions to show the new data
                    loadPredictions()
                }
            } catch (e: Exception) {
                Log.e("DatabaseDebug", "Error adding sample data", e)
            }
        }
    }
    
    fun clearAllData() {
        viewModelScope.launch {
            try {
                val currentUser = authRepository.getCurrentUser()
                if (currentUser != null) {
                    val deletedCount = repository.deleteAllPredictionsForUser(currentUser.id)
                    Log.d("DatabaseDebug", "Cleared $deletedCount predictions from database for user ID: ${currentUser.id}")
                    predictions = emptyList()
                }
            } catch (e: Exception) {
                Log.e("DatabaseDebug", "Error clearing database", e)
            }
        }
    }
}

@Composable
fun DatabaseDebugScreen(
    viewModel: DatabaseDebugViewModel = hiltViewModel()
) {
    val predictions = viewModel.getPredictions()
    
    LaunchedEffect(Unit) {
        viewModel.loadPredictions()
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Database Debug",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Button(
                onClick = { viewModel.clearAllData() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Clear All Data")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.loadPredictions() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Refresh Data")
            }
            
            Button(
                onClick = { viewModel.addSampleData() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Add Sample Data")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Total Records: ${predictions.size}",
            style = MaterialTheme.typography.bodyLarge
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (predictions.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No data in database",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Click 'Add Sample Data' to create test records",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn {
                items(predictions) { prediction ->
                    PredictionDebugItem(prediction = prediction)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun PredictionDebugItem(prediction: SkinPrediction) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "ID: ${prediction.id}",
                style = MaterialTheme.typography.bodyMedium
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = "Timestamp: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date(prediction.timestamp))}",
                style = MaterialTheme.typography.bodySmall
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = "Confidence: ${prediction.confidenceLevel}",
                style = MaterialTheme.typography.bodySmall
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Result: ${prediction.predictionResult}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}