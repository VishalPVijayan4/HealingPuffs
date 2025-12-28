package com.buildndeploy.healingpuffs.data.local.dao

import androidx.room.*
import com.buildndeploy.healingpuffs.data.local.entity.SmokeLogEntity
import com.buildndeploy.healingpuffs.data.local.entity.UrgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUrge(urge: UrgeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSmokeLog(log: SmokeLogEntity)

    @Query("SELECT * FROM urges ORDER BY timestamp DESC LIMIT 1")
    fun getLatestUrge(): Flow<UrgeEntity?>

    @Query("SELECT * FROM smoke_logs ORDER BY timestamp DESC LIMIT 1")
    fun getLatestSmoke(): Flow<SmokeLogEntity?>

    // ✅ SIMPLE: Just total counts by time ranges
    @Query("SELECT COUNT(*) as count FROM urges WHERE timestamp > :startTime")
    suspend fun getRecentUrgeCount(startTime: Long): Int

    @Query("SELECT COUNT(*) as count FROM urges")
    suspend fun getTotalUrgeCount(): Int
}

data class SimplePattern(val label: String, val count: Int)
