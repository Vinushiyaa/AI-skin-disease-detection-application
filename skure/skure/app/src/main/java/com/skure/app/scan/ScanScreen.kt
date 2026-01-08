package com.skure.app.scan

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.skure.app.BuildConfig
import com.skure.app.permissions.RequirePermissions
import java.io.File
import com.skure.app.utils.AnalysisHistory

@Composable
fun CameraScanScreen(onResult: (String) -> Unit) {
    RequirePermissions(
        permissions = listOf(Manifest.permission.CAMERA),
        rationale = "Camera needed to scan the affected area."
    ) {
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        val imageCapture = remember { ImageCapture.Builder().build() }
        val viewModel: ScanViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()
        
        val apiKey = "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"
        
        var lastCaptured by remember { mutableStateOf<File?>(null) }
        var lastUploadedUri by remember { mutableStateOf<Uri?>(null) }
        
        // Gallery picker launcher
        val galleryLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                try {
                    val inputStream = context.contentResolver.openInputStream(uri)
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    if (bitmap != null) {
                        lastUploadedUri = uri
                        lastCaptured = null // Clear camera capture when uploading
                        viewModel.analyzeImage(bitmap, apiKey)
                    }
                } catch (e: Exception) {
                    // Handle error
                }
            }
        }
        
        Column {
            AndroidView(
                factory = { ctx ->
                    val previewView = PreviewView(ctx)
                    val preview = Preview.Builder().build().also { it.setSurfaceProvider(previewView.surfaceProvider) }

                    val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                    cameraProviderFuture.addListener({
                        try {
                            val cameraProvider = cameraProviderFuture.get()
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                CameraSelector.DEFAULT_BACK_CAMERA,
                                preview,
                                imageCapture
                            )
                        } catch (_: Exception) {
                        }
                    }, ContextCompat.getMainExecutor(ctx))

                    previewView
                },
                modifier = Modifier.weight(1f, fill = true)
            )
            
            // Persist last analysis when result arrives
            LaunchedEffect(uiState.analysisResult) {
                val desc = uiState.analysisResult
                if (desc != null) {
                    // Save from camera capture
                    val img = lastCaptured
                    if (img != null) {
                        AnalysisHistory.saveLastAnalysis(
                            context = context,
                            sourceImage = img,
                            description = desc,
                            timestamp = System.currentTimeMillis()
                        )
                    }
                    // Save from gallery upload
                    else if (lastUploadedUri != null) {
                        try {
                            // Copy uploaded image to a temporary file
                            val tempFile = File(context.cacheDir, "uploaded_analysis_${System.currentTimeMillis()}.jpg")
                            context.contentResolver.openInputStream(lastUploadedUri!!)?.use { input ->
                                tempFile.outputStream().use { output ->
                                    input.copyTo(output)
                                }
                            }
                            AnalysisHistory.saveLastAnalysis(
                                context = context,
                                sourceImage = tempFile,
                                description = desc,
                                timestamp = System.currentTimeMillis()
                            )
                        } catch (e: Exception) {
                            // Handle error silently
                        }
                    }
                }
            }
            
            // Analysis Result Display
            if (uiState.analysisResult != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "🔍 Skin Analysis Report",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        // Display analysis with better formatting
                        val analysisText = uiState.analysisResult ?: "No analysis available"
                        Text(
                            text = analysisText,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.2
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(
                                onClick = { 
                                    viewModel.clearResult()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.secondary
                                )
                            ) {
                                Text("🔄 Retake & Analyze")
                            }
                        }
                    }
                }
            }
            
            // Error Display
            if (uiState.error != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Error:",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = uiState.error ?: "Unknown error occurred",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }
            }
            
            // Loading Indicator
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Analyzing...")
                    }
                }
            }
            
            val canAnalyze = !uiState.isLoading && uiState.cooldownSeconds == null
            
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        val file = File(context.cacheDir, "captured_image.jpg")
                        val output = ImageCapture.OutputFileOptions.Builder(file).build()
                        imageCapture.takePicture(
                            output,
                            ContextCompat.getMainExecutor(context),
                            object : ImageCapture.OnImageSavedCallback {
                                override fun onError(exc: ImageCaptureException) {}
                                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                                    lastCaptured = file
                                    lastUploadedUri = null // Clear upload when capturing
                                    if (bitmap != null) {
                                        viewModel.analyzeImage(bitmap, apiKey)
                                    }
                                }
                            }
                        )
                    },
                    modifier = Modifier.weight(1f),
                    enabled = canAnalyze
                ) { 
                    val label = when {
                        uiState.isLoading -> "Analyzing..."
                        uiState.cooldownSeconds != null -> "Wait ${uiState.cooldownSeconds}s"
                        uiState.analysisResult != null -> "Retake"
                        else -> "Scan"
                    }
                    Text(label)
                }
                
                Button(
                    onClick = { galleryLauncher.launch("image/*") },
                    modifier = Modifier.weight(1f),
                    enabled = canAnalyze,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Upload")
                }
            }
        }
    }
}