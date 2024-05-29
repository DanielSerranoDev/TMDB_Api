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

    //Peliculas por su genero
    @Query("SELECT * FROM showDB WHERE genres LIKE '%' || :genre || '%'")
    fun getShowsByGenre(genre: String): List<ResponseLocalUI>


    //Consulta por Id

    @Query("SELECT * FROM showDB WHERE id = :id")
    fun getShowById(id: String): ResponseLocalUI

    //Favorite
    @Query("UPDATE showDB SET isFavourite = :isFavourite WHERE id = :id")
    fun updateFavourite(id: String, isFavourite: Boolean)


    @Query("SELECT isFavourite FROM showDB WHERE id = :id")
    fun getShowStatusFavourite(id: String): Boolean

}