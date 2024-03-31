package com.riskee.gameappbyriski.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.domain.model.Game
import com.riskee.gameappbyriski.core.domain.usecase.GameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val gameUseCase: GameUseCase
) : ViewModel() {

    private val _gamesState = MutableStateFlow<Resource<List<Game>>>(Resource.INIT())
    val gamesState: StateFlow<Resource<List<Game>>> = _gamesState

    fun getAllGames() {
        viewModelScope.launch {
            gameUseCase.getAllGames().collect { resource ->
                _gamesState.value = resource
            }
        }
    }
}
