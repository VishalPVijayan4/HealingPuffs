package com.buildndeploy.healingpuffs.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buildndeploy.healingpuffs.domain.model.Pattern
import com.buildndeploy.healingpuffs.domain.model.SmokeLog
import com.buildndeploy.healingpuffs.domain.model.Trigger
import com.buildndeploy.healingpuffs.domain.model.Urge
import com.buildndeploy.healingpuffs.domain.repository.LogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: LogRepository
) : ViewModel() {

    // ✅ Use domain models, not entity
    private val _lastUrge = MutableStateFlow<Urge?>(null)
    val lastUrge: StateFlow<Urge?> = _lastUrge.asStateFlow()

    private val _lastSmoke = MutableStateFlow<SmokeLog?>(null)
    val lastSmoke: StateFlow<SmokeLog?> = _lastSmoke.asStateFlow()

    // ✅ Use Pattern model, not List<String>
    private val _patterns = MutableStateFlow<List<Pattern>>(emptyList())
    val patterns: StateFlow<List<Pattern>> = _patterns.asStateFlow()

    init {
        observeLatestEvents()
        observePatterns()
    }

    // ✅ Observe latest urge and smoke using repository methods
    private fun observeLatestEvents() {
        viewModelScope.launch {
            repository.getLatestUrge().collect { urge ->
                _lastUrge.value = urge
            }
        }

        viewModelScope.launch {
            repository.getLatestSmoke().collect { smoke ->
                _lastSmoke.value = smoke
            }
        }
    }

    // ✅ Observe patterns from repository
    private fun observePatterns() {
        viewModelScope.launch {
            repository.getPatterns().collect { patterns ->
                _patterns.value = patterns
            }
        }
    }

    // ✅ Correct signature: logUrge takes Urge object
    fun logUrge(intensity: Int = 5, triggers: List<Trigger> = emptyList(), delayed: Boolean = false) {
        viewModelScope.launch {
            val urge = Urge(
                intensity = intensity,
                triggers = triggers,
                timestamp = System.currentTimeMillis(),
                delayed = delayed
            )
            repository.logUrge(urge)
        }
    }

    // ✅ Correct signature: logSmoke takes SmokeLog object
    fun logSmoke(
        triggers: List<String> = emptyList(),
        mood: String = "",
        location: String = "",
        note: String = ""
    ) {
        viewModelScope.launch {
            val smokeLog = SmokeLog(
                triggers = triggers,
                mood = mood,
                location = location,
                note = note,
                timestamp = System.currentTimeMillis()
            )
            repository.logSmoke(smokeLog)
        }
    }
}
