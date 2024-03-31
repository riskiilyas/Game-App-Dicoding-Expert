package com.riskee.gameappbyriski.core.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import com.riskee.gameappbyriski.core.data.datasource.local.entity.DetailGameEntity
import com.riskee.gameappbyriski.core.data.datasource.local.entity.MinimumSystemRequirementsEntity
import com.riskee.gameappbyriski.core.data.datasource.local.entity.ScreenshotEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailGame(detailGameEntity: DetailGameEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMinimumSystemRequirements(minSystemRequirementsEntity: MinimumSystemRequirementsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScreenshots(screenshotEntities: List<ScreenshotEntity>)

    @Transaction
    @Query("SELECT * FROM detail_games WHERE id = :gameId")
    fun getDetailGameWithRequirementsAndScreenshots(gameId: Int): DetailGameWithRequirementsAndScreenshots?

    @Query("SELECT * FROM detail_games")
    fun getAllGames(): List<DetailGameEntity>

    @Transaction
    @Query("DELETE FROM detail_games WHERE id = :gameId")
    suspend fun deleteGame(gameId: Int)
}

data class DetailGameWithRequirementsAndScreenshots(
    @Embedded val detailGameEntity: DetailGameEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "detail_game_id"
    )
    val minSystemRequirementsEntity: MinimumSystemRequirementsEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "detail_game_id"
    )
    val screenshotEntities: List<ScreenshotEntity>
)