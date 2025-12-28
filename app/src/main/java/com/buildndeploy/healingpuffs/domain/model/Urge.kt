package com.buildndeploy.healingpuffs.domain.model

data class Urge(
    val id: Int = 0,
    val intensity: Int,
    val triggers: List<Trigger>,
    val timestamp: Long,
    val delayed: Boolean
)

data class SmokeLog(
    val id: Int = 0,
    val triggers: List<String>,
    val mood: String,
    val location: String,
    val note: String,
    val timestamp: Long
)

data class Trigger(
    val name: String
)

data class Pattern(
    val description: String
)
