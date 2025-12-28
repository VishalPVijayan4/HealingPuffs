package com.buildndeploy.healingpuffs.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// LogEntity.kt
@Entity(tableName = "logs")
data class LogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val type: String, // "URGE" or "SMOKE"
    val timestamp: Long,
    val notes: String? = null,
    val delayedMinutes: Int? = null
)
