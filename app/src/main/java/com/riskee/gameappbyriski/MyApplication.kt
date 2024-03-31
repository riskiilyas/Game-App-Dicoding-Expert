package com.riskee.gameappbyriski

import android.app.Application
import com.riskee.gameappbyriski.core.di.KoinModuleCore
import com.riskee.gameappbyriski.di.KoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    KoinModuleCore.getInjectionModule(),
                    KoinModule.getInjectionModule(),
                )
            )
        }
    }
}