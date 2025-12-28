package com.buildndeploy.healingpuffs.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.buildndeploy.healingpuffs.data.local.dao.TriggerConverter

@Entity(tableName = "urges")
@TypeConverters(TriggerConverter::class)
data class UrgeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val intensity: Int, // 1-5
    val triggers: List<String>,
    val timestamp: Long,
    val delayed: Boolean = false
)
