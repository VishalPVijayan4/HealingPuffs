package com.buildndeploy.healingpuffs.domain.model

enum class Trigger {
    STRESS, BOREDOM, HABIT, SOCIAL
}

data class Urge(
    val id: Long = 0,
    val intensity: Int, // 1-5
    val triggers: List<Trigger>,
    val timestamp: Long,
    val delayed: Boolean = false
)
