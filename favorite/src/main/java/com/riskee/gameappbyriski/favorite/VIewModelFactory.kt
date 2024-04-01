package com.riskee.gameappbyriski.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riskee.gameappbyriski.core.domain.usecase.GameUseCase
import com.riskee.gameappbyriski.favorite.detail_favorite.DetailFavoriteViewModel
import com.riskee.gameappbyriski.favorite.favorite.FavoriteViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val gameUseCase: GameUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(DetailFavoriteViewModel::class.java) -> {
                DetailFavoriteViewModel(gameUseCase) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(gameUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}