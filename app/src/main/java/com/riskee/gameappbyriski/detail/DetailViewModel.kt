package com.riskee.gameappbyriski.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {

    private val _detailGameState = MutableLiveData<Resource<DetailGame>>(Resource.INIT())
    val detailGameState: LiveData<Resource<DetailGame>> = _detailGameState

    private val _insertState = MutableLiveData(false)
    val insertState: LiveData<Boolean> = _insertState

    fun getDetailGame(id: Int) {
        viewModelScope.launch {
            gameUseCase.getDetailGame(id).collect { resource ->
                _detailGameState.postValue(resource)
            }
        }
    }

    fun addFavoriteGame(game: DetailGame) {
        viewModelScope.launch {
            _insertState.postValue(gameUseCase.insertFavoriteGame(game))
        }
    }

}
