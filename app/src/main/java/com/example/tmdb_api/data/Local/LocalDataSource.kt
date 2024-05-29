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

    override fun updateStatusFavourite(id: String, isFavourite: Boolean) {
        return dao.updateFavourite(id, isFavourite)
    }
    override fun getShowByGenre(genre: String): List<ResponseLocalUI> {
        return dao.getShowsByGenre(genre)
    }


    override fun getStatusFavourites(id: String): Boolean {
        return dao.getShowStatusFavourite(id)
    }

    override fun getShowById(id: String): ResponseLocalUI {
        return dao.getShowById(id)
    }

}