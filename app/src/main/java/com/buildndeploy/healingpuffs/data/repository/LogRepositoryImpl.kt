package com.buildndeploy.healingpuffs.data.repository

import com.buildndeploy.healingpuffs.data.local.dao.LogDao
import com.buildndeploy.healingpuffs.data.local.entity.SmokeLogEntity
import com.buildndeploy.healingpuffs.data.local.entity.UrgeEntity
import com.buildndeploy.healingpuffs.domain.model.Pattern
import com.buildndeploy.healingpuffs.domain.model.SmokeLog
import com.buildndeploy.healingpuffs.domain.model.Urge
import com.buildndeploy.healingpuffs.domain.model.Trigger
import com.buildndeploy.healingpuffs.domain.repository.LogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogRepositoryImpl @Inject constructor(
    private val logDao: LogDao
) : LogRepository {

    override suspend fun logUrge(urge: Urge) {
        val entity = UrgeEntity(
            intensity = urge.intensity,
            triggers = urge.triggers.map { it.name },
            timestamp = urge.timestamp,
            delayed = urge.delayed
        )
        logDao.insertUrge(entity)
    }

    override suspend fun logSmoke(smokeLog: SmokeLog) {
        val entity = SmokeLogEntity(
            triggers = smokeLog.triggers,
            mood = smokeLog.mood,
            location = smokeLog.location,
            note = smokeLog.note,
            timestamp = smokeLog.timestamp
        )
        logDao.insertSmokeLog(entity)
    }

    override fun getLatestUrge(): Flow<Urge?> =
        logDao.getLatestUrge().map { it?.toDomain() }

    override fun getLatestSmoke(): Flow<SmokeLog?> =
        logDao.getLatestSmoke().map { it?.toDomain() }

    override fun getPatterns(): Flow<List<Pattern>> =
        // Combine both pattern flows later
        flowOf(listOf())
}

// Extension functions needed
fun UrgeEntity.toDomain(): Urge = TODO()
fun SmokeLogEntity.toDomain(): SmokeLog = TODO()

