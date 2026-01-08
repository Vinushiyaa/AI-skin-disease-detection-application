package com.skure.app.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skure.app.repository.FoodAdvice
import com.skure.app.repository.FoodAdviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class FoodUi(
    val loading: Boolean = false,
    val error: String? = null,
    val advice: FoodAdvice? = null
)

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repo: FoodAdviceRepository
) : ViewModel() {
    private val _ui = MutableStateFlow(FoodUi())
    val ui: StateFlow<FoodUi> = _ui

    fun fetch(condition: String, preferences: String, allergies: String, apiKey: String) {
        if (condition.isBlank() || apiKey.isBlank()) return
        _ui.value = _ui.value.copy(loading = true, error = null)
        viewModelScope.launch {
            val result = repo.getAdvice(condition, preferences, allergies, apiKey)
            result.onSuccess {
                _ui.value = _ui.value.copy(loading = false, advice = it)
            }.onFailure { e ->
                _ui.value = _ui.value.copy(loading = false, error = e.message)
            }
        }
    }

    fun clear() { _ui.value = FoodUi() }
}
