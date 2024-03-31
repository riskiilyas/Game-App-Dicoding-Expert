package com.riskee.gameappbyriski.core.domain.usecase

import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.model.Game
import com.riskee.gameappbyriski.core.domain.repository.IGameRepository

class GameInteractor(private val repository: IGameRepository) : GameUseCase {
    override fun getDetailGame(id: Int) = repository.getDetailGame(id)
    override fun getAllGames() = repository.getAllGames()

    override fun getAllFavoriteGames() = repository.getAllFavoriteGames()
    override fun getDetailFavoriteGame(id: Int) = repository.getDetailFavoriteGame(id)
    override suspend fun insertFavoriteGame(game: DetailGame): Boolean =
        repository.insertFavoriteGame(game)

    override suspend fun deleteFavoriteGame(game: Game): Boolean =
        repository.deleteFavoriteGame(game)
}