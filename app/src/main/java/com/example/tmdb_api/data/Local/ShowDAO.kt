package com.example.tmdb_api.data.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.tmdb_api.domain.models.ResponseLocalUI
import com.example.tmdb_api.domain.models.Show

@Dao
interface ShowDAO {
    @Query("SELECT * FROM showDB")
    fun getAllShows(): List<ResponseLocalUI>

    @Insert
    fun insertAllShows(shows: List<ResponseLocalUI>)

    @Transaction
    fun updateShows(shows: List<ResponseLocalUI>) {
        deleteAll()
        insertAllShows(shows)
    }

    @Query("DELETE FROM showDB")
    fun deleteAll()

    @Query("UPDATE showDB SET isFavourite = :isFavourite WHERE id = :id")
    fun updateFavourite(id: Int, isFavourite: Boolean)


    @Query("SELECT isFavourite FROM showDB WHERE id = :id")
    fun getShowStatusFavourite(id: Int): Boolean

}