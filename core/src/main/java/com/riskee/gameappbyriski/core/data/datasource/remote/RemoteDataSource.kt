package com.riskee.gameappbyriski.core.data.datasource.remote

import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.data.datasource.remote.network.APIService
import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.model.Game
import com.riskee.gameappbyriski.core.utils.ResponseToModelMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RemoteDataSource(private val apiService: APIService) {

    fun getAllGames(): Flow<Resource<List<Game>>> = flow {
        try {
            emit(Resource.LOADING())
            val games = apiService.allGames()
            emit(Resource.SUCCESS(ResponseToModelMapper.gameResponseToModel(games)))
        } catch (e: Exception) {
            emit(Resource.ERROR("Error fetching games: ${e.message}"))
        }
    }

    fun getDetailGame(id: Int): Flow<Resource<DetailGame>> = flow {
        try {
            emit(Resource.LOADING())
            val detailGame = apiService.detailGame(id)
            emit(Resource.SUCCESS(ResponseToModelMapper.detailResponseToModel(detailGame)))
        } catch (e: HttpException) {
            emit(Resource.ERROR("HTTP Error: ${e.code()}"))
        } catch (e: Exception) {
            emit(Resource.ERROR("Error fetching game details: ${e.message}"))
        }
    }
}