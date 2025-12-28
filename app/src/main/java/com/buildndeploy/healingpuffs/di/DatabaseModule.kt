package com.buildndeploy.healingpuffs.di

import android.content.Context
import com.buildndeploy.healingpuffs.data.local.dao.LogDao
import com.buildndeploy.healingpuffs.data.local.database.HealingPuffsDatabase
import com.buildndeploy.healingpuffs.data.repository.LogRepositoryImpl
import com.buildndeploy.healingpuffs.domain.repository.LogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HealingPuffsDatabase {
        return HealingPuffsDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideLogDao(database: HealingPuffsDatabase): LogDao {
        return database.logDao()
    }

    @Provides
    @Singleton
    fun provideLogRepository(logDao: LogDao): LogRepository {
        return LogRepositoryImpl(logDao)
    }
}
