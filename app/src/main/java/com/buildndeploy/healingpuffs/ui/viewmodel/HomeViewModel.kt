package com.buildndeploy.healingpuffs.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buildndeploy.healingpuffs.domain.model.SmokeLog
import com.buildndeploy.healingpuffs.domain.model.Trigger
import com.buildndeploy.healingpuffs.domain.model.Urge
import com.buildndeploy.healingpuffs.domain.repository.LogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: LogRepository
) : ViewModel() {

    private val _lastUrge = MutableStateFlow<String?>(null)
    val lastUrge: StateFlow<String?> = _lastUrge.asStateFlow()

    private val _lastSmoke = MutableStateFlow<String?>(null)
    val lastSmoke: StateFlow<String?> = _lastSmoke.asStateFlow()

    private val _patterns = MutableStateFlow<List<String>>(emptyList())
    val patterns: StateFlow<List<String>> = _patterns.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            repository.getLatestUrge().collect { urge ->
                _lastUrge.value = urge?.let { formatTimeAgo(it.timestamp) }
            }
        }

        viewModelScope.launch {
            repository.getLatestSmoke().collect { smoke ->
                _lastSmoke.value = smoke?.let { formatTimeAgo(it.timestamp) }
            }
        }

        viewModelScope.launch {
            repository.getPatterns().collect { patterns ->
                _patterns.value = patterns.map { it.label }
            }
        }
    }

    fun logUrge() {
        viewModelScope.launch {
            val urge = Urge(
                intensity = 5,
                triggers = listOf(Trigger.STRESS),
                timestamp = System.currentTimeMillis(),
                delayed = false
            )
            repository.logUrge(urge)
        }
    }

    fun logSmoke() {
        viewModelScope.launch {
            val smokeLog = SmokeLog(
                triggers = listOf("Stress"),
                mood = null,
                location = null,
                note = null,
                timestamp = System.currentTimeMillis()
            )
            repository.logSmoke(smokeLog)
        }
    }

    private fun formatTimeAgo(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp

        val minutes = diff / (1000 * 60)
        val hours = diff / (1000 * 60 * 60)
        val days = diff / (1000 * 60 * 60 * 24)

        return when {
            minutes < 1 -> "Just now"
            minutes < 60 -> "${minutes}m ago"
            hours < 24 -> "${hours}h ago"
            days < 7 -> "${days}d ago"
            else -> SimpleDateFormat("MMM dd", Locale.getDefault()).format(Date(timestamp))
        }
    }
}
