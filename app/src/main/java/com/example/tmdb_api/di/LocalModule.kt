package com.example.tmdb_api.di

import android.content.Context
import androidx.room.Room
import com.example.tmdb_api.data.Local.LocalDataSource
import com.example.tmdb_api.data.Local.LocalDataSourceInterface
import com.example.tmdb_api.data.Local.ShowDAO
import com.example.tmdb_api.data.Local.ShowsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    fun providesDataBase(@ApplicationContext context: Context): ShowsDataBase {
        return Room.databaseBuilder(
            context,
            ShowsDataBase::class.java, "tmdb_database"
        ).build()
    }

    @Provides
    fun providesShowDao(db: ShowsDataBase): ShowDAO{
        return db.showDAO()
    }


    @Provides
    fun providesLocalDataSourceInterface(localDataSource: LocalDataSource): LocalDataSourceInterface {
        return localDataSource
    }
}