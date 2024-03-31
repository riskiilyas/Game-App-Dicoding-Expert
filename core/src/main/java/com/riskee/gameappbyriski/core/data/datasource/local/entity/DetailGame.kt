package com.riskee.gameappbyriski.core.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_games")
data class DetailGameEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val thumbnail: String,
    val status: String,
    @ColumnInfo(name = "short_description") val shortDescription: String,
    val description: String,
    @ColumnInfo(name = "game_url") val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "freetogame_profile_url") val freetogameProfileUrl: String
)

@Entity(tableName = "minimum_system_requirements")
data class MinimumSystemRequirementsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val os: String,
    val processor: String,
    val memory: String,
    val graphics: String,
    val storage: String,
    @ColumnInfo(name = "detail_game_id") val detailGameId: Int
)

@Entity(tableName = "screenshots")
data class ScreenshotEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "screenshot_image") val image: String,
    @ColumnInfo(name = "detail_game_id") val detailGameId: Int
)