package com.riskee.gameappbyriski.core.di

import android.content.Context
import androidx.room.Room
import com.riskee.gameappbyriski.core.data.GameRepositoryImpl
import com.riskee.gameappbyriski.core.data.datasource.local.LocalDataSource
import com.riskee.gameappbyriski.core.data.datasource.local.room.GameDb
import com.riskee.gameappbyriski.core.data.datasource.remote.RemoteDataSource
import com.riskee.gameappbyriski.core.data.datasource.remote.network.APIService
import com.riskee.gameappbyriski.core.data.datasource.remote.network.ApiConfig
import com.riskee.gameappbyriski.core.domain.repository.IGameRepository
import com.riskee.gameappbyriski.core.domain.usecase.GameInteractor
import com.riskee.gameappbyriski.core.domain.usecase.GameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideApiService() = ApiConfig.getApiService()


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GameDb {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("riskee".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            GameDb::class.java,
            "game_app_by_riski_db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(db: GameDb) = LocalDataSource(db)


    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: APIService) = RemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideRepository(local: LocalDataSource, remote: RemoteDataSource): IGameRepository {
        return GameRepositoryImpl(remote, local)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: IGameRepository): GameUseCase {
        return GameInteractor(repository)
    }

}