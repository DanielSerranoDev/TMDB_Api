package com.example.tmdb_api.data.Local

import com.example.tmdb_api.domain.models.ResponseLocalUI

interface LocalDataSourceInterface{

    fun insertShows(shows:ResponseLocalUI)
    fun updateStatusFavourite(id:String,isFavourite:Boolean)
    fun getStatusFavourites(id:String):Boolean

    fun getShowIdDB(id:String):ResponseLocalUI
    fun deleteShow(id:String)


}