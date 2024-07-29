package com.example.tmdb_api.data.Local

import com.example.tmdb_api.domain.models.ResponseLocalUI
import javax.inject.Inject

class LocalDataSource
    @Inject
    constructor(
        private val dao: ShowDAO,
    ) : LocalDataSourceInterface {
        override fun insertShows(show: ResponseLocalUI) = dao.insertShow(show)

        override fun getShowIdDB(id: String): ResponseLocalUI = dao.getShowById(id)

        override fun deleteShow(id: String) = dao.deleteShow(id)
    }
