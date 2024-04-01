package com.riskee.gameappbyriski.di

import android.content.Context
import com.riskee.gameappbyriski.favorite.detail_favorite.DetailFavoriteActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleInterface::class])
interface ModuleFavoriteDetail {

    fun inject(activity: DetailFavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleInterface: FavoriteModuleInterface): Builder
        fun build(): ModuleFavoriteDetail
    }
}