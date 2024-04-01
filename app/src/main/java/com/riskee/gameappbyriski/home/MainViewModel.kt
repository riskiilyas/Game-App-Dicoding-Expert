package com.riskee.gameappbyriski.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.domain.model.Game
import com.riskee.gameappbyriski.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val gameUseCase: GameUseCase
) : ViewModel() {

    private val _gamesState = MutableLiveData<Resource<List<Game>>>(Resource.INIT())
    val gamesState: LiveData<Resource<List<Game>>> = _gamesState

    fun getAllGames() {
        viewModelScope.launch {
            gameUseCase.getAllGames().collect { resource ->
                _gamesState.postValue(resource)
            }
        }
    }
}
