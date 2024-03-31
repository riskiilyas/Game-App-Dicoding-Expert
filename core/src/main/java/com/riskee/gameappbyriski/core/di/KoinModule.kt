package com.riskee.gameappbyriski.core.di

import androidx.room.Room
import com.riskee.gameappbyriski.core.data.GameRepositoryImpl
import com.riskee.gameappbyriski.core.data.datasource.local.LocalDataSource
import com.riskee.gameappbyriski.core.data.datasource.local.room.GameDb
import com.riskee.gameappbyriski.core.data.datasource.remote.RemoteDataSource
import com.riskee.gameappbyriski.core.data.datasource.remote.network.ApiConfig
import com.riskee.gameappbyriski.core.domain.repository.IGameRepository
import com.riskee.gameappbyriski.core.domain.usecase.GameInteractor
import com.riskee.gameappbyriski.core.domain.usecase.GameUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object KoinModuleCore {
    fun getInjectionModule(): Module {
        return module {
            single { ApiConfig.getApiService(get()) }
            single {
                Room.databaseBuilder(
                    get(),
                    GameDb::class.java,
                    "game_app_by_riski_db"
                ).build()
            }
            single { LocalDataSource(get()) }
            single { RemoteDataSource(get()) }
            single<IGameRepository> { GameRepositoryImpl(get(), get()) }
            single<GameUseCase> { GameInteractor(get()) }
        }
    }
}