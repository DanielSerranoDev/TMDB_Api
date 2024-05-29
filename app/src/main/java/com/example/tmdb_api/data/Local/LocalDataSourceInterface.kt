package com.example.tmdb_api.data.Local

import com.example.tmdb_api.domain.models.ResponseLocalUI
import com.example.tmdb_api.domain.models.Show

interface LocalDataSourceInterface{
    fun getAllShows():List<ResponseLocalUI>
    fun insertAllShows(shows:List<ResponseLocalUI>)
    fun updateShows(shows:List<ResponseLocalUI>)
    fun updateStatusFavourite(id:String,isFavourite:Boolean)
    fun getStatusFavourites(id:String):Boolean

    fun getShowByGenre(genre: String): List<ResponseLocalUI>

    fun getShowById(id:String):ResponseLocalUI

}