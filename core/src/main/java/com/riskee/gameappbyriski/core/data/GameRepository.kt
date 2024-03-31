package com.riskee.gameappbyriski.core.data

import com.riskee.gameappbyriski.core.data.datasource.local.LocalDataSource
import com.riskee.gameappbyriski.core.data.datasource.remote.RemoteDataSource
import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.model.Game
import com.riskee.gameappbyriski.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow


class GameRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IGameRepository {

    override fun getAllGames(): Flow<Resource<List<Game>>> = remoteDataSource.getAllGames()

    override fun getDetailGame(id: Int): Flow<Resource<DetailGame>> =
        remoteDataSource.getDetailGame(id)

    override fun getAllFavoriteGames(): Flow<Resource<List<Game>>> = localDataSource.getAllGames()

    override fun getDetailFavoriteGame(id: Int): Flow<Resource<DetailGame>> = localDataSource.getDetailGame(id)

    override suspend fun insertFavoriteGame(game: DetailGame): Boolean = localDataSource.insertFavoriteGame(game)

    override suspend fun deleteFavoriteGame(game: Game): Boolean = localDataSource.deleteFavoriteGame(game)
}
