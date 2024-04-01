package com.riskee.gameappbyriski.di

import com.riskee.gameappbyriski.core.domain.usecase.GameUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleInterface {

    fun provideGameUseCase(): GameUseCase

}