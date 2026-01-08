package com.skure.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import com.skure.app.database.DatabaseInitializer
import com.skure.app.ui.SkureTheme
import dagger.hilt.android.AndroidEntryPoint
import android.util.Log

@AndroidEntryPoint
class MainActivity: ComponentActivity(){
    private lateinit var databaseInitializer: DatabaseInitializer
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize database with sample data for testing
        databaseInitializer = DatabaseInitializer(this)
        databaseInitializer.initializeDatabase()
        
        // Perform a query to verify data was inserted
        Thread {
            try {
                Thread.sleep(2000) // Wait for UI to initialize
                val users = databaseInitializer.queryAllUsers()
                val predictions = databaseInitializer.queryDatabase()
                Log.d("MainActivity", "Database query returned ${users.size} users and ${predictions.size} predictions")
            } catch (e: Exception) {
                Log.e("MainActivity", "Error querying database", e)
            }
        }.start()
        
        setContent{
            SkureTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SkureAppRoot()
                }
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // Comment out database closing to keep it active
        // if (::databaseInitializer.isInitialized) {
        //     databaseInitializer.closeDatabase()
        // }
    }
}