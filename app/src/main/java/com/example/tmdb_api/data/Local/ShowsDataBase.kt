package com.example.tmdb_api.data.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tmdb_api.domain.models.ResponseLocalUI


@Database(entities = [ResponseLocalUI::class], version = 1)
@TypeConverters(Converters::class)
abstract class ShowsDataBase : RoomDatabase() {
    abstract fun showDAO(): ShowDAO
}