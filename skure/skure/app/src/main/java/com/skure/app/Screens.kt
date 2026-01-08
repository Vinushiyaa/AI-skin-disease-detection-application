package com.skure.app

import android.Manifest
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.skure.app.domain.Disease
import com.skure.app.domain.Hospital
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.skure.app.hospitals.HospitalUtils
import com.skure.app.hospitals.HospitalsViewModel
import com.skure.app.hospitals.HospitalsUiState
import com.skure.app.domain.Product
import com.skure.app.permissions.RequirePermissions
import com.skure.app.domain.UserProfile
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.TextButton
import com.skure.app.scan.CameraScanScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.skure.app.chat.ChatViewModel
import com.skure.app.BuildConfig
import com.skure.app.food.FoodViewModel
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.asImageBitmap
import java.text.DateFormat
import java.util.Date
import com.skure.app.utils.AnalysisHistory
import com.skure.app.utils.AnalysisRecord
import androidx.compose.material3.MenuAnchorType
import kotlinx.coroutines.launch
import com.skure.app.repository.AuthRepository
import com.skure.app.auth.AuthViewModel
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Alignment

@Composable
fun AuthScreen(onAuthed: (UserProfile)->Unit, onSignup: (UserProfile)->Unit){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    
    // Use Hilt to inject the AuthViewModel
    val authViewModel: AuthViewModel = hiltViewModel()
    val authRepository = authViewModel.authRepository
    
    var name by remember{ mutableStateOf("") }
    var email by remember{ mutableStateOf("") }
    var phone by remember{ mutableStateOf("") }
    var pass by remember{ mutableStateOf("") }
    var confirm by remember{ mutableStateOf("") }
    var isLogin by remember{ mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    
    val canSubmit = if (isLogin) email.isNotBlank() && pass.isNotBlank() else name.isNotBlank() && email.isNotBlank() && pass.length >= 6 && pass == confirm
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 100.dp)
            .verticalScroll(rememberScrollState())
    ){
        
        if (!isLogin){
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
        }
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        if (!isLogin){
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
        }
        
        OutlinedTextField(
            value = pass, 
            onValueChange = { pass = it }, 
            label = { Text("Password") }, 
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )
        if (!isLogin){
            OutlinedTextField(
                value = confirm,
                onValueChange = { confirm = it },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
        }
        
        if (error != null) {
            Text(
                text = error!!, 
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        
        Button(
            onClick = {
                isLoading = true
                error = null
                scope.launch {
                    try {
                        if (isLogin) {
                            // Perform actual login
                            val result = authRepository.login(email, pass)
                            if (result.isSuccess) {
                                isLoading = false
                                onAuthed(result.getOrNull()!!)
                            } else {
                                isLoading = false
                                error = result.exceptionOrNull()?.message ?: "Login failed"
                            }
                        } else {
                            // Perform actual registration
                            val result = authRepository.register(name, email, phone, pass)
                            if (result.isSuccess) {
                                isLoading = false
                                onSignup(result.getOrNull()!!)
                            } else {
                                isLoading = false
                                error = result.exceptionOrNull()?.message ?: "Registration failed"
                            }
                        }
                    } catch (e: Exception) {
                        isLoading = false
                        error = e.message
                    }
                }
            }, 
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            enabled = canSubmit && !isLoading
        ){ 
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(if (isLogin) "Sign In" else "Register")
            }
        }
        
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = if (isLogin) "Don't have an account? " else "Already have an account? ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            TextButton(
                onClick = { 
                    isLogin = !isLogin
                    error = null
                },
                modifier = Modifier.padding(start = 4.dp)
            ){
                Text(
                    text = if (isLogin) "Register" else "Sign In",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun AnalysisHistoryScreen() {
    val context = LocalContext.current
    var history by remember { mutableStateOf<List<AnalysisRecord>>(emptyList()) }
    val refreshHistory = remember(context) {
        {
            history = AnalysisHistory.loadAllAnalyses(context)
        }
    }

    LaunchedEffect(Unit) {
        refreshHistory()
    }

    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Analysis History", style = MaterialTheme.typography.headlineSmall)
            if (history.isNotEmpty()) {
                TextButton(onClick = {
                    AnalysisHistory.clearAllHistory(context)
                    refreshHistory()
                }) {
                    Text("Clear All")
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        if (history.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = true),
                contentAlignment = Alignment.Center
            ) {
                Text("No analysis found yet.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = true),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(history, key = { it.id }) { record ->
                    AnalysisHistoryCard(
                        record = record,
                        onDelete = {
                            AnalysisHistory.deleteAnalysis(context, record.id)
                            refreshHistory()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun AnalysisHistoryCard(
    record: AnalysisRecord,
    onDelete: () -> Unit
) {
    val bitmap = remember(record.imagePath) {
        runCatching { BitmapFactory.decodeFile(record.imagePath) }.getOrNull()
    }
    val timestampText = remember(record.timestamp) {
        DateFormat.getDateTimeInstance().format(Date(record.timestamp))
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            Modifier
            .padding(16.dp)
        ) {
            Text(
                text = timestampText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(8.dp))
            bitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(8.dp))
            }
            Text(record.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDelete) {
                    Text("Delete", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodRegulationScreen(){
    val vm: FoodViewModel = hiltViewModel()
    val ui by vm.ui.collectAsState()
    val apiKey = "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"
    
    var condition by remember { mutableStateOf("") }
    var preferences by remember { mutableStateOf("") }
    var allergies by remember { mutableStateOf("") }
    val conditionOptions = listOf(
        "Eczema","Psoriasis","Acne","Rosacea","Vitiligo","Urticaria (Hives)","Contact Dermatitis","Tinea (Ringworm)"
    )
    var expanded by remember { mutableStateOf(false) }
    val scroll = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .imePadding()
            .navigationBarsPadding()
            .padding(16.dp)
            .verticalScroll(scroll)
    ){
        Text("AI Dietary Advisor", style = MaterialTheme.typography.headlineSmall)
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
            OutlinedTextField(
                value = if (condition.isBlank()) "Select a condition" else condition,
                onValueChange = {},
                readOnly = true,
                label = { Text("Skin Condition") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true)
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                conditionOptions.forEach { opt ->
                    DropdownMenuItem(text = { Text(opt) }, onClick = { condition = opt; expanded = false })
                }
            }
        }
        OutlinedTextField(value = preferences, onValueChange = { preferences = it }, label = { Text("Dietary Preferences (e.g., Vegetarian, Vegan, Gluten-free)") }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
        OutlinedTextField(value = allergies, onValueChange = { allergies = it }, label = { Text("Allergies & Other Conditions (e.g., Nuts, Dairy, Hypertension)") }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
        Button(
            onClick = { vm.fetch(condition, preferences, allergies, apiKey) },
            enabled = condition.isNotBlank() && !ui.loading,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) { Text("⚙️  Get Recommendations") }
        if (ui.loading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
        }
        if (ui.error != null) {
            Text("Error: ${ui.error}", color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }
        ui.advice?.let { advice ->
            Text("Recommended Foods", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 12.dp))
            advice.eat.forEach { item -> Text("• $item") }

            Text("Foods to Avoid", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 12.dp))
            advice.avoid.forEach { item -> Text("• $item") }

            if (advice.substitutes.isNotEmpty()) {
                Text("Suitable Substitutes", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 12.dp))
                advice.substitutes.forEach { item -> Text("• $item") }
            }

            if (advice.lifestyle.isNotEmpty()) {
                Text("Lifestyle Changes", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 12.dp))
                advice.lifestyle.forEach { item -> Text("• $item") }
            }

            advice.disclaimer?.let { d -> Text(d, modifier = Modifier.padding(top = 12.dp)) }
        }
        Spacer(Modifier.height(24.dp))
    }
}
@Composable
fun HomeScreen(profile: UserProfile, onScan:()->Unit, onHospitals:()->Unit, onChat:()->Unit, onFood:()->Unit, onConditions:()->Unit, onEditProfile: ()->Unit, onAnalysisHistory:()->Unit){
    val menu = listOf(
        "Scan & Analyze" to onScan,
        "Nearby Hospitals" to onHospitals,
        "Chatbot" to onChat,
        "Food Regulation" to onFood,
        "Conditions" to onConditions,
        "Analysis History" to onAnalysisHistory
    )
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(24.dp))
                Text("Profile", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 16.dp))
                Text("Name: ${profile.fullName}", modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
                Text("Email: ${profile.email}", modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
                if (profile.phone.isNotBlank()) {
                    Text("Phone: ${profile.phone}", modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
                }
                TextButton(onClick = onEditProfile, modifier = Modifier.padding(horizontal = 8.dp)) { Text("Edit Profile") }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 80.dp)
        ){
            menu.forEach { (label, action) ->
                Button(onClick = action, modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) { Text(label) }
            }
            
            // Medical Disclaimer
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "⚠️ Medical Disclaimer",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "This tool provides AI-based analysis and is NOT a substitute for professional medical advice. Please consult a dermatologist for accurate diagnosis and treatment.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
        }
    }
}

@Composable
fun ScanScreen(onResult:(String)->Unit){
    CameraScanScreen(onResult)
}

@Composable
fun ResultScreen(onHospitals: ()->Unit, onChat: ()->Unit){
    val disease = remember {
        Disease(
            id = "dermatitis",
            name = "Dermatitis",
            description = "Skin inflammation causing itch and redness.",
            firstAid = listOf("Wash gently","Apply moisturizer","Avoid irritants"),
            foodChart = listOf("Hydrate well","Omega-3 rich foods","Avoid spicy foods"),
            tags = listOf("skin"),
            products = listOf(
                Product("Soothing Cream","https://example.com/cream"),
                Product("Hypoallergenic Soap","https://example.com/soap")
            )
        )
    }
    Column(Modifier.padding(16.dp)){
        Text(disease.name, style = MaterialTheme.typography.headlineMedium)
        Text(disease.description, modifier = Modifier.padding(top = 8.dp))
        Text("First Aid", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 12.dp))
        disease.firstAid.forEach { Text("• $it") }
        Text("Food Chart", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 12.dp))
        disease.foodChart.forEach { Text("• $it") }
        Text("Products", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 12.dp))
        disease.products.forEach { Text("• ${it.name} → ${it.url}") }
        Button(onClick = onHospitals, modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
            Text("Find Nearby Hospitals")
        }
        Button(onClick = onChat, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
            Text("Ask Chatbot")
        }
    }
}

@Composable
fun HospitalsScreen(){
    RequirePermissions(
        permissions = listOf(Manifest.permission.ACCESS_FINE_LOCATION),
        rationale = "Location is needed to find hospitals near you."
    ){
        val vm: HospitalsViewModel = viewModel()
        val ui by vm.ui.collectAsState()
        var specializationInput by remember { mutableStateOf("") }
        val scroll = rememberScrollState()
        val context = LocalContext.current
        
        Column(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(16.dp)
                .verticalScroll(scroll)
        ){
            Text("Nearby Hospitals", style = MaterialTheme.typography.headlineSmall)
            
            Text(
                "Find specialized hospitals near you",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
            
            OutlinedTextField(
                value = specializationInput,
                onValueChange = { specializationInput = it },
                label = { Text("Specialization (e.g., kidney, cardiology, neurology)") },
                placeholder = { Text("Leave blank for all hospitals") },
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                enabled = !ui.loading
            )
            
            Button(
                onClick = { vm.loadNearbyHospitals(specializationInput.trim()) },
                enabled = !ui.loading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(if (specializationInput.isBlank()) "Find All Hospitals" else "Search Specialized Hospitals")
            }
            
            if (ui.specialization.isNotBlank()) {
                Text(
                    "Showing: ${ui.specialization} hospitals",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
            
            ui.infoMessage?.let { message ->
                Text(
                    message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            when {
                ui.loading -> {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                    )
                    Text("Searching nearby hospitals...", modifier = Modifier.padding(top = 8.dp))
                }
                ui.error != null -> {
                    Text(
                        "Error: ${ui.error}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
                ui.hasSearched && ui.error == null && ui.hospitals.isEmpty() && !ui.loading -> {
                    Text(
                        "No hospitals found. Try adjusting your search.",
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
                else -> {
                    if (ui.hospitals.isNotEmpty()) {
                        Text(
                            "Found ${ui.hospitals.size} hospital(s)",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
                        )
                    }
                    ui.hospitals.forEach { h ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(Modifier.padding(12.dp)){
                                Text(
                                    h.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    "%.1f km away".format(h.distanceKm),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                                if (h.address.isNotBlank()) {
                                    Text(
                                        h.address,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )
                                }
                                if (!h.phone.isNullOrBlank()) {
                                    Text(
                                        "Phone: ${h.phone}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                                if (!h.hours.isNullOrBlank()) {
                                    Text(
                                        "Hours: ${h.hours}",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                                if (!h.specialization.isNullOrBlank()) {
                                    Text(
                                        "Specialization: ${h.specialization}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.tertiary,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                                
                                // Action buttons for each hospital
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Button(
                                        onClick = { 
                                            com.skure.app.hospitals.HospitalUtils.openDirectionsInMaps(
                                                context, 
                                                h.latitude, 
                                                h.longitude, 
                                                h.name
                                            )
                                        },
                                        modifier = Modifier.weight(1f).padding(end = 4.dp)
                                    ) {
                                        Text("Directions")
                                    }
                                    Button(
                                        onClick = { 
                                            com.skure.app.hospitals.HospitalUtils.openLocationInMaps(
                                                context, 
                                                h.latitude, 
                                                h.longitude, 
                                                h.name
                                            )
                                        },
                                        modifier = Modifier.weight(1f).padding(start = 4.dp)
                                    ) {
                                        Text("View Map")
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun ChatScreen(){
    val vm: ChatViewModel = hiltViewModel()
    val ui by vm.ui.collectAsState()
    val apiKey = "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"
    
    var text by remember { mutableStateOf("") }
    val scroll = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .imePadding()
            .navigationBarsPadding()
            .padding(16.dp)
    ){
        Text("Chat", style = MaterialTheme.typography.headlineSmall)
        if (ui.error != null) {
            Text("Error: ${ui.error}", color = MaterialTheme.colorScheme.error)
        }
        Column(Modifier.weight(1f).fillMaxWidth().verticalScroll(scroll)) {
            ui.messages.forEach { m -> Text("${m.role}: ${m.text}") }
        }
        if (ui.loading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))
        }
        OutlinedTextField(value = text, onValueChange = { text = it }, modifier = Modifier.fillMaxWidth())
        Button(onClick = { vm.send(text, apiKey); text = "" }, enabled = !ui.loading, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) { Text("Send") }
    }
}



@Composable
fun ProfileScreen(profile: UserProfile, onSave: (UserProfile)->Unit, onBack: ()->Unit){
    var name by remember { mutableStateOf(profile.fullName) }
    var email by remember { mutableStateOf(profile.email) }
    var phone by remember { mutableStateOf(profile.phone) }
    val options = listOf(
        "Diabetes","Hypertension","Heart Disease","High Cholesterol","Asthma/COPD","Thyroid Disorder","Kidney Disease","Liver Disease","Stroke/TIA","Cancer","Surgery","Smoking","Alcohol Use","Drug Allergy","Food Allergy","Environmental Allergy"
    )
    var selected by remember { mutableStateOf(profile.medicalHistory.toMutableSet()) }
    var other by remember { mutableStateOf(profile.otherHistory) }
    val canSave = name.isNotBlank() && email.isNotBlank()
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .imePadding()
            .navigationBarsPadding()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ){
        Text("Edit Profile", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone Number") }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))

        Text("Medical History", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 16.dp))
        options.chunked(2).forEach { row ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                row.forEach { opt ->
                    val checked = opt in selected
                    Row(Modifier.weight(1f)) {
                        Checkbox(checked = checked, onCheckedChange = { isChecked ->
                            if (isChecked) selected.add(opt) else selected.remove(opt)
                            selected = selected.toMutableSet()
                        })
                        Text(opt, modifier = Modifier.padding(top = 12.dp))
                    }
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
        }
        OutlinedTextField(value = other, onValueChange = { other = it }, label = { Text("Other conditions") }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))

        Row(Modifier.fillMaxWidth().padding(top = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)){
            Button(onClick = onBack, modifier = Modifier.weight(1f)) { Text("Cancel") }
            Button(
                onClick = { onSave(UserProfile(fullName = name, email = email, phone = phone, medicalHistory = selected.toList(), otherHistory = other)) },
                enabled = canSave,
                modifier = Modifier.weight(1f)
            ) { Text("Save") }
        }
    }
}
