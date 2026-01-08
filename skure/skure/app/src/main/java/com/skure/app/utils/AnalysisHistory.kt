package com.skure.app.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import org.json.JSONArray
import org.json.JSONObject

data class AnalysisRecord(
    val id: String,
    val imagePath: String,
    val description: String,
    val timestamp: Long
)

object AnalysisHistory {
    private const val HISTORY_DIR = "analysis_history"
    private const val HISTORY_META = "history_list.json"
    
    fun saveLastAnalysis(context: Context, sourceImage: File, description: String, timestamp: Long) {
        // Create history directory if it doesn't exist
        val historyDir = File(context.filesDir, HISTORY_DIR)
        if (!historyDir.exists()) {
            historyDir.mkdirs()
        }
        
        // Generate unique ID for this analysis
        val id = "analysis_$timestamp"
        
        // Copy image with unique name
        val destImage = File(historyDir, "$id.jpg")
        sourceImage.inputStream().use { input ->
            FileOutputStream(destImage).use { output ->
                input.copyTo(output)
            }
        }
        
        // Load existing history
        val historyList = loadAllAnalyses(context).toMutableList()
        
        // Add new record
        val newRecord = AnalysisRecord(
            id = id,
            imagePath = destImage.absolutePath,
            description = description,
            timestamp = timestamp
        )
        historyList.add(0, newRecord) // Add to beginning (most recent first)
        
        // Save updated history
        saveHistoryList(context, historyList)
    }
    
    fun loadLastAnalysis(context: Context): AnalysisRecord? {
        return loadAllAnalyses(context).firstOrNull()
    }
    
    fun loadAllAnalyses(context: Context): List<AnalysisRecord> {
        val metaFile = File(context.filesDir, HISTORY_META)
        if (!metaFile.exists()) return emptyList()
        
        return try {
            val jsonArray = JSONArray(metaFile.readText())
            val records = mutableListOf<AnalysisRecord>()
            
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val id = obj.optString("id")
                val imagePath = obj.optString("imagePath")
                val description = obj.optString("description")
                val timestamp = obj.optLong("timestamp", 0L)
                
                if (id.isNotBlank() && imagePath.isNotBlank() && timestamp > 0L) {
                    records.add(AnalysisRecord(id, imagePath, description, timestamp))
                }
            }
            records
        } catch (_: Exception) {
            emptyList()
        }
    }
    
    fun deleteAnalysis(context: Context, id: String) {
        val historyList = loadAllAnalyses(context).toMutableList()
        val recordToDelete = historyList.find { it.id == id }
        
        recordToDelete?.let {
            // Delete image file
            val imageFile = File(it.imagePath)
            if (imageFile.exists()) {
                imageFile.delete()
            }
            
            // Remove from list
            historyList.remove(it)
            
            // Save updated list
            saveHistoryList(context, historyList)
        }
    }
    
    fun clearAllHistory(context: Context) {
        // Delete all image files
        val historyDir = File(context.filesDir, HISTORY_DIR)
        if (historyDir.exists()) {
            historyDir.listFiles()?.forEach { it.delete() }
            historyDir.delete()
        }
        
        // Delete metadata file
        val metaFile = File(context.filesDir, HISTORY_META)
        if (metaFile.exists()) {
            metaFile.delete()
        }
    }
    
    private fun saveHistoryList(context: Context, records: List<AnalysisRecord>) {
        val jsonArray = JSONArray()
        records.forEach { record ->
            val obj = JSONObject()
                .put("id", record.id)
                .put("imagePath", record.imagePath)
                .put("description", record.description)
                .put("timestamp", record.timestamp)
            jsonArray.put(obj)
        }
        File(context.filesDir, HISTORY_META).writeText(jsonArray.toString())
    }
}
