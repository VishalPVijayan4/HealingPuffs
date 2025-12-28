package com.buildndeploy.healingpuffs.data.local.dao

import androidx.room.*
import com.buildndeploy.healingpuffs.data.local.entity.LogEntity
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

    // ✅ ADD THESE METHODS if missing
    @Query("SELECT * FROM urges ORDER BY timestamp DESC")
    fun getAllUrges(): Flow<List<UrgeEntity>>

    @Query("SELECT * FROM smoke_logs ORDER BY timestamp DESC")
    fun getAllSmokes(): Flow<List<SmokeLogEntity>>

    @Query("SELECT COUNT(*) FROM urges WHERE timestamp > :startTime")
    suspend fun getRecentUrgeCount(startTime: Long): Int

    @Query("SELECT COUNT(*) FROM urges")
    suspend fun getTotalUrgeCount(): Int
}

