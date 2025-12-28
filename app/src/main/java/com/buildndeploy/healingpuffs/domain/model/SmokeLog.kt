package com.buildndeploy.healingpuffs.domain.model

data class SmokeLog(
    val id: Long = 0,
    val triggers: List<String>,
    val mood: String?,
    val location: String?,
    val note: String?,
    val timestamp: Long
)
