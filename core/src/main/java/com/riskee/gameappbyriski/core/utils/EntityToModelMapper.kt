package com.riskee.gameappbyriski.core.utils

import com.riskee.gameappbyriski.core.data.datasource.local.dao.DetailGameWithRequirementsAndScreenshots
import com.riskee.gameappbyriski.core.data.datasource.local.entity.DetailGameEntity
import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.model.Game
import com.riskee.gameappbyriski.core.domain.model.MinimumSystemRequirements
import com.riskee.gameappbyriski.core.domain.model.Screenshot

object EntityToModelMapper {
    fun gameEntityToModel(detailGameEntity: List<DetailGameEntity>): List<Game> {
        return detailGameEntity.map {
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

    fun detailGameEntityToModel(detailGameEntity: DetailGameWithRequirementsAndScreenshots): DetailGame {
        val minSystemRequirements = MinimumSystemRequirements(
            os = detailGameEntity.minSystemRequirementsEntity?.os ?: "",
            processor = detailGameEntity.minSystemRequirementsEntity?.processor ?: "",
            memory = detailGameEntity.minSystemRequirementsEntity?.memory ?: "",
            graphics = detailGameEntity.minSystemRequirementsEntity?.graphics ?: "",
            storage = detailGameEntity.minSystemRequirementsEntity?.storage ?: ""
        )

        val screenshots = detailGameEntity.screenshotEntities.map {
            Screenshot(
                image = it.image,
                id = it.id
            )
        }

        return DetailGame(
            id = detailGameEntity.detailGameEntity.id,
            title = detailGameEntity.detailGameEntity.title,
            thumbnail = detailGameEntity.detailGameEntity.thumbnail,
            status = detailGameEntity.detailGameEntity.status,
            shortDescription = detailGameEntity.detailGameEntity.shortDescription,
            description = detailGameEntity.detailGameEntity.description,
            gameUrl = detailGameEntity.detailGameEntity.gameUrl,
            genre = detailGameEntity.detailGameEntity.genre,
            platform = detailGameEntity.detailGameEntity.platform,
            publisher = detailGameEntity.detailGameEntity.publisher,
            developer = detailGameEntity.detailGameEntity.developer,
            releaseDate = detailGameEntity.detailGameEntity.releaseDate,
            freetogameProfileUrl = detailGameEntity.detailGameEntity.freetogameProfileUrl,
            minSystemRequirements = minSystemRequirements,
            screenshots = screenshots
        )
    }
}