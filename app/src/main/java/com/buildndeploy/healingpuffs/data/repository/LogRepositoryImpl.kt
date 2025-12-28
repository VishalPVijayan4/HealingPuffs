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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.util.Calendar
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
        logDao.getLatestUrge().map { entity ->
            entity?.toDomain()
        }

    override fun getLatestSmoke(): Flow<SmokeLog?> =
        logDao.getLatestSmoke().map { entity ->
            entity?.toDomain()
        }

    // ✅ ADD THESE IMPLEMENTATIONS
    override fun getAllUrges(): Flow<List<Urge>> =
        logDao.getAllUrges().map { entities ->
            entities.map { it.toDomain() }
        }

    override fun getAllSmokes(): Flow<List<SmokeLog>> =
        logDao.getAllSmokes().map { entities ->
            entities.map { it.toDomain() }
        }

    override fun getPatterns(): Flow<List<Pattern>> =
        combine(
            logDao.getLatestUrge(),
            logDao.getLatestSmoke()
        ) { latestUrge, latestSmoke ->
            buildPatternList(latestUrge, latestSmoke)
        }

    private fun buildPatternList(
        latestUrge: UrgeEntity?,
        latestSmoke: SmokeLogEntity?
    ): List<Pattern> {
        val patterns = mutableListOf<Pattern>()

        if (latestUrge == null && latestSmoke == null) {
            patterns.add(
                Pattern(
                    description = "No data yet. Start tracking!",
                )
            )
            return patterns
        }

        latestUrge?.let { urgeEntity ->
            val hourOfDay = Calendar.getInstance().apply {
                timeInMillis = urgeEntity.timestamp
            }.get(Calendar.HOUR_OF_DAY)
            patterns.add(
                Pattern(
                    description = "No data yet. Start tracking!",
                )
            )
        }

        latestSmoke?.let {
            patterns.add(
                Pattern(
                    description = "No data yet. Start tracking!",
                )
            )
        }

        return patterns
    }
}

private fun UrgeEntity.toDomain(): Urge {
    return Urge(
        id = this.id,
        intensity = this.intensity,
        triggers = this.triggers.map { triggerString ->
            Trigger(name = triggerString)  // ✅ Simply create Trigger with the name
        },
        timestamp = this.timestamp,
        delayed = this.delayed
    )
}


// Extension functions
/*private fun UrgeEntity.toDomain(): Urge {
    return Urge(
        id = this.id,
        intensity = this.intensity,
        triggers = this.triggers.mapNotNull { triggerString ->
            try {
                Trigger.valueOf(triggerString.uppercase())
            } catch (e: IllegalArgumentException) {
                null
            }
        },
        timestamp = this.timestamp,
        delayed = this.delayed
    )
}*/

private fun SmokeLogEntity.toDomain(): SmokeLog {
    return SmokeLog(
        id = this.id,
        triggers = this.triggers,
        mood = this.mood,
        location = this.location,
        note = this.note,
        timestamp = this.timestamp
    )
}
