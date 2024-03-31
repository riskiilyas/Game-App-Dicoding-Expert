package com.riskee.gameappbyriski.core.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class DetailGameResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("status") val status: String,
    @SerializedName("short_description") val shortDescription: String,
    @SerializedName("description") val description: String,
    @SerializedName("game_url") val gameUrl: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("platform") val platform: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("developer") val developer: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("freetogame_profile_url") val freetogameProfileUrl: String,
    @SerializedName("minimum_system_requirements") val minSystemRequirements: MinimumSystemRequirementsResponse,
    @SerializedName("screenshots") val screenshots: List<ScreenshotResponse>
)

data class MinimumSystemRequirementsResponse(
    @SerializedName("os") val os: String,
    @SerializedName("processor") val processor: String,
    @SerializedName("memory") val memory: String,
    @SerializedName("graphics") val graphics: String,
    @SerializedName("storage") val storage: String
)

data class ScreenshotResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String
)
