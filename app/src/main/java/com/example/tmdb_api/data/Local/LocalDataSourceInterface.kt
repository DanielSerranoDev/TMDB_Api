package com.example.tmdb_api.data.Local

import com.example.tmdb_api.domain.models.ResponseLocalUI

interface LocalDataSourceInterface{

    fun insertShows(shows:ResponseLocalUI)

    fun getShowIdDB(id:String):ResponseLocalUI
    fun deleteShow(id:String)


}