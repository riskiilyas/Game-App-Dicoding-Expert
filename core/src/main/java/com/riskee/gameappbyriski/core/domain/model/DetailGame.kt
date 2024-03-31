package com.riskee.gameappbyriski.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailGame(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val status: String,
    val shortDescription: String,
    val description: String,
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val releaseDate: String,
    val freetogameProfileUrl: String,
    val minSystemRequirements: MinimumSystemRequirements,
    val screenshots: List<Screenshot>
) : Parcelable

@Parcelize
data class MinimumSystemRequirements(
    val os: String,
    val processor: String,
    val memory: String,
    val graphics: String,
    val storage: String
) : Parcelable

@Parcelize
data class Screenshot(
    val id: Int,
    val image: String
) : Parcelable
