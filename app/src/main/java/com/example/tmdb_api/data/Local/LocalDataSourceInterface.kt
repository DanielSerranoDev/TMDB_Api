package com.example.tmdb_api.data.Local

import com.example.tmdb_api.domain.models.ResponseLocalUI
import com.example.tmdb_api.domain.models.Show

interface LocalDataSourceInterface{

    fun insertShows(shows:ResponseLocalUI)
    fun updateStatusFavourite(id:String,isFavourite:Boolean)
    fun getStatusFavourites(id:String):Boolean

    fun getShowById(id:String):ResponseLocalUI



}