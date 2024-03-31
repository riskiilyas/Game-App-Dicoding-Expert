package com.riskee.gameappbyriski.core.utils

import com.riskee.gameappbyriski.core.data.datasource.local.entity.DetailGameEntity
import com.riskee.gameappbyriski.core.data.datasource.local.entity.MinimumSystemRequirementsEntity
import com.riskee.gameappbyriski.core.data.datasource.local.entity.ScreenshotEntity
import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.model.MinimumSystemRequirements
import com.riskee.gameappbyriski.core.domain.model.Screenshot

object ModelToEntityMapper {
    fun detailGameToDetailGameEntity(detailGame: DetailGame): DetailGameEntity {
        return DetailGameEntity(
            id = detailGame.id,
            title = detailGame.title,
            thumbnail = detailGame.thumbnail,
            shortDescription = detailGame.shortDescription,
            gameUrl = detailGame.gameUrl,
            genre = detailGame.genre,
            platform = detailGame.platform,
            publisher = detailGame.publisher,
            developer = detailGame.developer,
            releaseDate = detailGame.releaseDate,
            freetogameProfileUrl = detailGame.freetogameProfileUrl,
            status = detailGame.status,
            description = detailGame.description
        )
    }

    fun minSystemRequirementsToMinimumSystemRequirementsEntity(
        minSystemRequirements: MinimumSystemRequirements,
        detailGameId: Int
    ): MinimumSystemRequirementsEntity {
        return MinimumSystemRequirementsEntity(
            os = minSystemRequirements.os,
            processor = minSystemRequirements.processor,
            memory = minSystemRequirements.memory,
            graphics = minSystemRequirements.graphics,
            storage = minSystemRequirements.storage,
            detailGameId = detailGameId
        )
    }

    fun screenshotsToScreenshotEntities(
        screenshots: List<Screenshot>,
        detailGameId: Int
    ): List<ScreenshotEntity> {
        return screenshots.map {
            ScreenshotEntity(
                image = it.image,
                detailGameId = detailGameId
            )
        }
    }
}