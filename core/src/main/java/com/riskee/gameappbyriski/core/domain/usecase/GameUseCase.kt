package com.riskee.gameappbyriski.core.domain.usecase

import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getDetailGame(id: Int): Flow<Resource<DetailGame>>
    fun getAllGames(): Flow<Resource<List<Game>>>
    fun getAllFavoriteGames(): Flow<Resource<List<Game>>>
    fun getDetailFavoriteGame(id: Int): Flow<Resource<DetailGame>>
    suspend fun insertFavoriteGame(game: DetailGame): Boolean
    suspend fun deleteFavoriteGame(game: Game): Boolean
}