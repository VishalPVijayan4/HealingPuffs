package com.buildndeploy.healingpuffs.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.buildndeploy.healingpuffs.data.local.dao.LogDao
import com.buildndeploy.healingpuffs.data.local.dao.TriggerConverter
import com.buildndeploy.healingpuffs.data.local.entity.LogEntity
import com.buildndeploy.healingpuffs.data.local.entity.SmokeLogEntity
import com.buildndeploy.healingpuffs.data.local.entity.UrgeEntity

// HealingPuffsDatabase.kt
@Database(
    entities = [LogEntity::class,UrgeEntity::class, SmokeLogEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TriggerConverter::class)
abstract class HealingPuffsDatabase : RoomDatabase() {
    abstract fun logDao(): LogDao

    companion object {
        @Volatile
        private var INSTANCE: HealingPuffsDatabase? = null

        fun getDatabase(context: Context): HealingPuffsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HealingPuffsDatabase::class.java,
                    "healing_puffs_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

