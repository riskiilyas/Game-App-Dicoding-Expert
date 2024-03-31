package com.riskee.gameappbyriski.core.data.datasource.remote.network

import com.riskee.gameappbyriski.core.data.datasource.remote.response.DetailGameResponse
import com.riskee.gameappbyriski.core.data.datasource.remote.response.GameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("games")
    suspend fun allGames(
    ): List<GameResponse>

    @GET("game")
    suspend fun detailGame(
        @Query("id") id: Int
    ): DetailGameResponse

}