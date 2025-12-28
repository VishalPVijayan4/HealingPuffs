package com.buildndeploy.healingpuffs.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.buildndeploy.healingpuffs.data.local.dao.TriggerConverter

@Entity(tableName = "smoke_logs")
@TypeConverters(TriggerConverter::class)
data class SmokeLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val triggers: List<String>,
    val mood: String?,
    val location: String?,
    val note: String?,
    val timestamp: Long
)
