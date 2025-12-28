package com.buildndeploy.healingpuffs.domain.usecase

import com.buildndeploy.healingpuffs.domain.model.SmokeLog
import com.buildndeploy.healingpuffs.domain.repository.LogRepository
import javax.inject.Inject

class LogSmokeUseCase @Inject constructor(
    private val repository: LogRepository
) {
    suspend operator fun invoke(smokeLog: SmokeLog) {
        repository.logSmoke(smokeLog)
    }
}
