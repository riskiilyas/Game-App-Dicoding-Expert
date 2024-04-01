package com.riskee.gameappbyriski.favorite.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.domain.model.Game
import com.riskee.gameappbyriski.core.domain.usecase.GameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    private val _favoriteGamesFlow = MutableLiveData<Resource<List<Game>>>(Resource.INIT())
    val favoriteGamesFlow: LiveData<Resource<List<Game>>> = _favoriteGamesFlow

    fun getAllFavoriteGames() {
        viewModelScope.launch(Dispatchers.IO) {
            gameUseCase.getAllFavoriteGames().collect {
                _favoriteGamesFlow.postValue(it)
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