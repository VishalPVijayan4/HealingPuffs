package com.buildndeploy.healingpuffs.domain.usecase

import com.buildndeploy.healingpuffs.domain.model.Pattern
import com.buildndeploy.healingpuffs.domain.repository.LogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPatternsUseCase @Inject constructor(
    private val repository: LogRepository
) {
    operator fun invoke(): Flow<List<Pattern>> = repository.getPatterns()
}
