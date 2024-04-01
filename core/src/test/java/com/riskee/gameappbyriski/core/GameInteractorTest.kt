package com.riskee.gameappbyriski.core

import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.model.Game
import com.riskee.gameappbyriski.core.domain.model.MinimumSystemRequirements
import com.riskee.gameappbyriski.core.domain.model.Screenshot
import com.riskee.gameappbyriski.core.domain.repository.IGameRepository
import com.riskee.gameappbyriski.core.domain.usecase.GameInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GameInteractorTest {

    @Mock
    private lateinit var repository: IGameRepository
    private lateinit var gameInteractor: GameInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        gameInteractor = GameInteractor(repository)
    }

    @Test
    fun `test getAllGames returns games from repository`(): Unit = runTest {
        val games = listOf(
            Game(
                1,
                "Game 1",
                "https://example.com/game1.png",
                "Short description of Game 1",
                "https://example.com/game1",
                "Action",
                "PC",
                "Publisher 1",
                "Developer 1",
                "2022-01-01",
                "https://example.com/game1-profile"
            ),
            Game(
                2,
                "Game 2",
                "https://example.com/game2.png",
                "Short description of Game 2",
                "https://example.com/game2",
                "RPG",
                "PlayStation",
                "Publisher 2",
                "Developer 2",
                "2023-02-15",
                "https://example.com/game2-profile"
            )
        )
        val resource = Resource.SUCCESS(games)
        `when`(repository.getAllGames()).thenReturn(flowOf(resource))

        val result = gameInteractor.getAllGames().first()

        verify(repository, times(1)).getAllGames()
        assert(result is Resource.SUCCESS)
        assert((result as Resource.SUCCESS).result == games)
    }

    @Test
    fun `test getDetailGame returns detail game from repository`() = runTest {
        val gameId = 1
        val detailGame = DetailGame(
            1,
            "Game 1",
            "https://example.com/game1.png",
            "Released",
            "Short description of Game 1",
            "Detailed description of Game 1",
            "https://example.com/game1",
            "Action",
            "PC",
            "Publisher 1",
            "Developer 1",
            "2022-01-01",
            "https://example.com/game1-profile",
            MinimumSystemRequirements(
                "Windows 10",
                "Intel Core i5",
                "8 GB RAM",
                "NVIDIA GeForce GTX 1060",
                "50 GB available space"
            ),
            listOf(
                Screenshot(1, "https://example.com/game1-screenshot1.png"),
                Screenshot(2, "https://example.com/game1-screenshot2.png")
            )
        )
        val resource = Resource.SUCCESS(detailGame)
        `when`(repository.getDetailGame(gameId)).thenReturn(flowOf(resource))

        val result = gameInteractor.getDetailGame(gameId).first()

        verify(repository, times(1)).getDetailGame(gameId)
        assert(result is Resource.SUCCESS)
        assert((result as Resource.SUCCESS).result == detailGame)
    }

    @Test
    fun `test insertFavoriteGame inserts game in repository`() = runTest {
        val detailGame = DetailGame(
            1,
            "Game 1",
            "https://example.com/game1.png",
            "Released",
            "Short description of Game 1",
            "Detailed description of Game 1",
            "https://example.com/game1",
            "Action",
            "PC",
            "Publisher 1",
            "Developer 1",
            "2022-01-01",
            "https://example.com/game1-profile",
            MinimumSystemRequirements(
                "Windows 10",
                "Intel Core i5",
                "8 GB RAM",
                "NVIDIA GeForce GTX 1060",
                "50 GB available space"
            ),
            listOf(
                Screenshot(1, "https://example.com/game1-screenshot1.png"),
                Screenshot(2, "https://example.com/game1-screenshot2.png")
            )
        )

        `when`(repository.insertFavoriteGame(detailGame)).thenReturn(true)

        val result = gameInteractor.insertFavoriteGame(detailGame)

        verify(repository, times(1)).insertFavoriteGame(detailGame)
        assert(result)
    }

}