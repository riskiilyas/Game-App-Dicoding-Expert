package com.riskee.gameappbyriski.favorite.detail_favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.domain.model.DetailGame
import com.riskee.gameappbyriski.core.domain.usecase.GameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFavoriteViewModel(private val gameUseCase: GameUseCase) :
    ViewModel() {

    private val _detailGameFlow = MutableLiveData<Resource<DetailGame>>(Resource.INIT())
    val detailGameFlow: LiveData<Resource<DetailGame>> = _detailGameFlow

    fun getDetailFavoriteGame(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            gameUseCase.getDetailFavoriteGame(id).collect {
                _detailGameFlow.postValue(it)
            }
        }
    }
}
