package com.riskee.gameappbyriski.di

import com.riskee.gameappbyriski.detail.DetailViewModel
import com.riskee.gameappbyriski.home.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object KoinModule {
    fun getInjectionModule(): Module {
        return module {
            viewModel { MainViewModel(get()) }
            viewModel { DetailViewModel(get()) }
        }
    }
}