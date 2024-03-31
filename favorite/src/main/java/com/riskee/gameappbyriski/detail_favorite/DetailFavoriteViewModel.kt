package com.riskee.gameappbyriski.detail_favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.usecase.GameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailFavoriteViewModel(private val gameUseCase: GameUseCase): ViewModel() {

    private val _detailGameFlow = MutableStateFlow<Resource<DetailGame>>(Resource.INIT())
    val detailGameFlow: StateFlow<Resource<DetailGame>> = _detailGameFlow

    fun getDetailFavoriteGame(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            gameUseCase.getDetailFavoriteGame(id).collect {
                _detailGameFlow.value = it
            }
        }
    }
}
