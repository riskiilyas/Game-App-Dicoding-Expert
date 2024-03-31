package com.riskee.gameappbyriski.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.usecase.GameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(private val gameUseCase: GameUseCase) : ViewModel() {

    private val _detailGameState = MutableStateFlow<Resource<DetailGame>>(Resource.INIT())
    val detailGameState: StateFlow<Resource<DetailGame>> = _detailGameState

    private val _insertState = MutableStateFlow(false)
    val insertState: StateFlow<Boolean> = _insertState

    fun getDetailGame(id: Int) {
        viewModelScope.launch {
            gameUseCase.getDetailGame(id).collect { resource ->
                _detailGameState.value = resource
            }
        }
    }

    fun addFavoriteGame(game: DetailGame) {
        viewModelScope.launch {
            _insertState.value = gameUseCase.insertFavoriteGame(game)
        }
    }
}
