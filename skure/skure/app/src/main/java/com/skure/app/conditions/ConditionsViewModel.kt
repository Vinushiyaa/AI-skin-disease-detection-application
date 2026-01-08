package com.skure.app.conditions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skure.app.repository.AuthRepository
import com.skure.app.database.SkinDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ConditionsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val databaseRepository: SkinDatabaseRepository
) : ViewModel() {
    
    fun saveConditionViewed(conditionKey: String) {
        viewModelScope.launch {
            try {
                val currentUser = authRepository.getCurrentUser()
                if (currentUser != null) {
                    val conditionInfo = ConditionsData[conditionKey]
                    if (conditionInfo != null) {
                        // Create a summary of the condition information
                        val description = conditionInfo.summary
                        val treatments = buildString {
                            if (conditionInfo.causes.isNotEmpty()) {
                                append("Causes: ${conditionInfo.causes.joinToString(", ")}\n")
                            }
                            if (conditionInfo.otc.isNotEmpty()) {
                                append("OTC Treatments: ${conditionInfo.otc.joinToString(", ")}\n")
                            }
                            if (conditionInfo.diet.isNotEmpty()) {
                                append("Dietary Recommendations: ${conditionInfo.diet.joinToString(", ")}\n")
                            }
                        }
                        
                        // Save to database
                        databaseRepository.insertConditionInfo(
                            currentUser.id,
                            conditionInfo.label,
                            description,
                            treatments
                        )
                    }
                }
            } catch (e: Exception) {
                // Handle error silently or log it
            }
        }
    }
}