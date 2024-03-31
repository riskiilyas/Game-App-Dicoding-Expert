package com.riskee.gameappbyriski.di

import com.riskee.gameappbyriski.core.data.GameRepositoryImpl
import com.riskee.gameappbyriski.core.data.datasource.local.LocalDataSource
import com.riskee.gameappbyriski.core.data.datasource.local.room.GameDb
import com.riskee.gameappbyriski.core.data.datasource.remote.RemoteDataSource
import com.riskee.gameappbyriski.core.data.datasource.remote.network.ApiConfig
import com.riskee.gameappbyriski.detail_favorite.DetailFavoriteViewModel
import com.riskee.gameappbyriski.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object KoinModuleFavorite {
    fun getInjectionModule(): Module {
        return module {
            viewModel { FavoriteViewModel(get()) }
            viewModel { DetailFavoriteViewModel(get()) }
        }
    }
}