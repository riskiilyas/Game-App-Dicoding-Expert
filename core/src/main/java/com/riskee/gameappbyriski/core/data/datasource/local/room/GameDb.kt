package com.riskee.gameappbyriski.core.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.riskee.gameappbyriski.core.data.datasource.local.dao.GameDetailDao
import com.riskee.gameappbyriski.core.data.datasource.local.entity.DetailGameEntity
import com.riskee.gameappbyriski.core.data.datasource.local.entity.MinimumSystemRequirementsEntity
import com.riskee.gameappbyriski.core.data.datasource.local.entity.ScreenshotEntity

@Database(
    entities = [DetailGameEntity::class, MinimumSystemRequirementsEntity::class, ScreenshotEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GameDb : RoomDatabase() {
    abstract fun gameDetailDao(): GameDetailDao
}