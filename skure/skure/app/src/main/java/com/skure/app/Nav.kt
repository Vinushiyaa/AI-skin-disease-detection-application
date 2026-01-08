package com.skure.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.skure.app.domain.UserProfile
import com.skure.app.conditions.ConditionsScreen
import com.skure.app.ui.DatabaseDebugScreen

@Composable
fun SkureAppRoot(){
    val nav = rememberNavController()
    var isRegistered by remember { mutableStateOf(false) }
    var profile by remember { mutableStateOf(UserProfile(fullName = "User", email = "", phone = "")) }
    NavHost(navController = nav, startDestination = "auth"){
        composable("auth"){ 
            AuthScreen(
                onAuthed = { p -> profile = p; isRegistered = true; nav.navigate("home"){ popUpTo("auth"){ inclusive = true } } },
                onSignup = { p -> profile = p; isRegistered = true; nav.navigate("home"){ popUpTo("auth"){ inclusive = true } } }
            ) 
        }
        composable("home"){ 
            HomeScreen(
                profile = profile,
                onScan = { nav.navigate("scan") },
                onHospitals = { nav.navigate("hospitals") },
                onChat = { nav.navigate("chat") },
                onFood = { nav.navigate("food") },
                onConditions = { nav.navigate("conditions") },
                onEditProfile = { nav.navigate("profile") },
                onAnalysisHistory = { nav.navigate("analysisHistory") }
            ) 
        }
        composable("scan"){ ScanScreen(onResult = { id -> nav.navigate("result/$id") }) }
        composable("result/{id}"){
            ResultScreen(onHospitals = { nav.navigate("hospitals") }, onChat = { nav.navigate("chat") })
        }
        composable("hospitals"){ HospitalsScreen() }
        composable("chat"){ ChatScreen() }
        composable("food"){ FoodRegulationScreen() }
        composable("conditions"){ ConditionsScreen() }
        composable("profile"){ 
            ProfileScreen(
                profile = profile,
                onSave = { updated: UserProfile -> profile = updated; nav.popBackStack() },
                onBack = { nav.popBackStack() }
            )
        }
        composable("analysisHistory"){ AnalysisHistoryScreen() }
        composable("databaseDebug"){ DatabaseDebugScreen() }
    }
}



