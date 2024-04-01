package com.riskee.gameappbyriski.di

import android.content.Context
import com.riskee.gameappbyriski.favorite.favorite.FavoriteActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleInterface::class])
interface ModuleFavorite {

    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleInterface: FavoriteModuleInterface): Builder
        fun build(): ModuleFavorite
    }
}