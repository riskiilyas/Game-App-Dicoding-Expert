package com.riskee.gameappbyriski.core.data.datasource.local

import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.data.datasource.local.room.GameDb
import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.model.Game
import com.riskee.gameappbyriski.core.utils.EntityToModelMapper
import com.riskee.gameappbyriski.core.utils.ModelToEntityMapper
import kotlinx.coroutines.flow.flow

class LocalDataSource(private val gameDb: GameDb) {

    fun getAllGames() = flow {
        try {
            emit(Resource.LOADING())
            val games = gameDb.gameDetailDao().getAllGames()
            emit(Resource.SUCCESS(EntityToModelMapper.gameEntityToModel(games)))
        } catch (e: Exception) {
            emit(Resource.ERROR("Error fetching games: ${e.message}"))
        }
    }

    fun getDetailGame(id: Int) = flow {
        try {
            emit(Resource.LOADING())
            val gameDetail = gameDb.gameDetailDao().getDetailGameWithRequirementsAndScreenshots(id)
            emit(Resource.SUCCESS(EntityToModelMapper.detailGameEntityToModel(gameDetail!!)))
        } catch (e: Exception) {
            emit(Resource.ERROR("Error fetching games: ${e.message}"))
        }
    }

    suspend fun insertFavoriteGame(game: DetailGame): Boolean {
        return try {
            gameDb.gameDetailDao()
                .insertDetailGame(ModelToEntityMapper.detailGameToDetailGameEntity(game))
            gameDb.gameDetailDao().insertMinimumSystemRequirements(
                ModelToEntityMapper.minSystemRequirementsToMinimumSystemRequirementsEntity(
                    game.minSystemRequirements,
                    game.id
                )
            )
            gameDb.gameDetailDao().insertScreenshots(
                ModelToEntityMapper.screenshotsToScreenshotEntities(
                    game.screenshots,
                    game.id
                )
            )

            true
        } catch (_: Exception) {
            false
        }
    }

    suspend fun deleteFavoriteGame(game: Game): Boolean {
        return try {
            gameDb.gameDetailDao().deleteGame(game.id)
            true
        } catch (_: Exception) {
            false
        }
    }
}