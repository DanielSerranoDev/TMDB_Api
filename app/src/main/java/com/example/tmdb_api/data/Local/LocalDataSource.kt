package com.example.tmdb_api.data.Local

import com.example.tmdb_api.domain.models.ResponseLocalUI
import com.example.tmdb_api.domain.models.Show
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: ShowDAO): LocalDataSourceInterface {

    override fun insertShows(show: ResponseLocalUI) {
        return dao.insertShow(show)
    }


    override fun updateStatusFavourite(id: String, isFavourite: Boolean) {
        return dao.updateFavourite(id, isFavourite)
    }


    override fun getStatusFavourites(id: String): Boolean {
        return dao.getShowStatusFavourite(id)
    }

    override fun getShowIdDB(id: String): ResponseLocalUI {
        return dao.getShowById(id)
    }

    override fun deleteShow(id: String) {
        return dao.deleteShow(id)
    }


}