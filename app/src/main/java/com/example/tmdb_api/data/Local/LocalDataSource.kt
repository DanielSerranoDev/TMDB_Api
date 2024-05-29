package com.example.tmdb_api.data.Local

import com.example.tmdb_api.domain.models.ResponseLocalUI
import com.example.tmdb_api.domain.models.Show
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: ShowDAO): LocalDataSourceInterface {
    override fun getAllShows(): List<ResponseLocalUI> {
        return dao.getAllShows()
    }

    override fun insertAllShows(shows: List<ResponseLocalUI>) {
        return dao.insertAllShows(shows)
    }

    override fun updateShows(shows: List<ResponseLocalUI>) {
        return dao.updateShows(shows)
    }


    override fun updateStatusFavourite(id: Int, isFavourite: Boolean) {
        return dao.updateFavourite(id, isFavourite)
    }

    override fun getStatusFavourites(id: Int): Boolean {
        return dao.getShowStatusFavourite(id)
    }


}