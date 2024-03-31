package com.riskee.gameappbyriski.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.model.Game
import com.riskee.gameappbyriski.core.domain.usecase.GameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    private val _favoriteGamesFlow = MutableStateFlow<Resource<List<Game>>>(Resource.INIT())
    val favoriteGamesFlow: StateFlow<Resource<List<Game>>> = _favoriteGamesFlow

    fun getAllFavoriteGames() {
        viewModelScope.launch(Dispatchers.IO) {
            gameUseCase.getAllFavoriteGames().collect {
                _favoriteGamesFlow.value = it
            }
        }
    }

    fun deleteFavoriteGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            gameUseCase.deleteFavoriteGame(game)
            getAllFavoriteGames()
        }
    }
}