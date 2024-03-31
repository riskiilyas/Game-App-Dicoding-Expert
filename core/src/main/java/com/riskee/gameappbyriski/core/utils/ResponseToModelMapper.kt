package com.riskee.gameappbyriski.core.utils

import com.riskee.gameappbyriski.core.data.datasource.remote.response.DetailGameResponse
import com.riskee.gameappbyriski.core.data.datasource.remote.response.GameResponse
import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.model.Game
import com.riskee.gameappbyriski.core.domain.model.MinimumSystemRequirements
import com.riskee.gameappbyriski.core.domain.model.Screenshot

object ResponseToModelMapper {
    fun detailResponseToModel(response: DetailGameResponse): DetailGame {
        val minSystemRequirements = MinimumSystemRequirements(
            os = response.minSystemRequirements.os,
            processor = response.minSystemRequirements.processor,
            memory = response.minSystemRequirements.memory,
            graphics = response.minSystemRequirements.graphics,
            storage = response.minSystemRequirements.storage
        )

        val screenshots = response.screenshots.map {
            Screenshot(
                image = it.image,
                id = it.id
            )
        }

        return DetailGame(
            id = response.id,
            title = response.title,
            thumbnail = response.thumbnail,
            status = response.status,
            shortDescription = response.shortDescription,
            description = response.description,
            gameUrl = response.gameUrl,
            genre = response.genre,
            platform = response.platform,
            publisher = response.publisher,
            developer = response.developer,
            releaseDate = response.releaseDate,
            freetogameProfileUrl = response.freetogameProfileUrl,
            minSystemRequirements = minSystemRequirements,
            screenshots = screenshots
        )
    }

    fun gameResponseToModel(response: List<GameResponse>): List<Game> {
        return response.map {
            Game(
                id = it.id,
                title = it.title,
                thumbnail = it.thumbnail,
                shortDescription = it.shortDescription,
                gameUrl = it.gameUrl,
                genre = it.genre,
                platform = it.platform,
                publisher = it.publisher,
                developer = it.developer,
                releaseDate = it.releaseDate,
                freetogameProfileUrl = it.freetogameProfileUrl
            )
        }
    }
}