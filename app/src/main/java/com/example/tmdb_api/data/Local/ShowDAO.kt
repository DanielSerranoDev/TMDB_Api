package com.example.tmdb_api.data.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.tmdb_api.domain.models.ResponseLocalUI
import com.example.tmdb_api.domain.models.Show

@Dao
interface ShowDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShow(show: ResponseLocalUI)


    @Query("DELETE FROM showDB WHERE id = :id")
    fun deleteShow(id: String)

    //Consulta por Id

    @Query("SELECT * FROM showDB WHERE id = :id")
    fun getShowById(id: String): ResponseLocalUI


    @Query("UPDATE showDB SET isFavourite = :isFavourite WHERE id = :id")
    fun updateFavourite(id: String, isFavourite: Boolean)


    @Query("SELECT isFavourite FROM showDB WHERE id = :id")
    fun getShowStatusFavourite(id: String): Boolean

}