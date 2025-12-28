package com.buildndeploy.healingpuffs.domain.repository

import com.buildndeploy.healingpuffs.data.local.entity.LogEntity
import com.buildndeploy.healingpuffs.domain.model.Pattern
import com.buildndeploy.healingpuffs.domain.model.SmokeLog
import com.buildndeploy.healingpuffs.domain.model.Urge
import kotlinx.coroutines.flow.Flow

// repository/LogRepository.kt (interface)


interface LogRepository {
    suspend fun logUrge(urge: Urge)
    suspend fun logSmoke(smokeLog: SmokeLog)
    fun getLatestUrge(): Flow<Urge?>
    fun getLatestSmoke(): Flow<SmokeLog?>
    fun getAllUrges(): Flow<List<Urge>>
    fun getAllSmokes(): Flow<List<SmokeLog>>
    fun getPatterns(): Flow<List<Pattern>>  // ✅ This exists - should work
}


