package com.buildndeploy.healingpuffs.domain.repository

import com.buildndeploy.healingpuffs.domain.model.Pattern
import com.buildndeploy.healingpuffs.domain.model.SmokeLog
import com.buildndeploy.healingpuffs.domain.model.Urge
import kotlinx.coroutines.flow.Flow

interface LogRepository {
    suspend fun logUrge(urge: Urge)
    suspend fun logSmoke(smokeLog: SmokeLog)
    fun getLatestUrge(): Flow<Urge?>
    fun getLatestSmoke(): Flow<SmokeLog?>
    fun getPatterns(): Flow<List<Pattern>>
}