package com.buildndeploy.healingpuffs.domain.usecase

import com.buildndeploy.healingpuffs.domain.model.Urge
import com.buildndeploy.healingpuffs.domain.repository.LogRepository
import javax.inject.Inject

class LogUrgeUseCase @Inject constructor(
    private val repository: LogRepository
) {
    suspend operator fun invoke(urge: Urge) {
        repository.logUrge(urge)
    }
}
