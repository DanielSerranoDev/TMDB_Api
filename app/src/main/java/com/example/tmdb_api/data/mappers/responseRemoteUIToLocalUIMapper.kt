package com.example.tmdb_api.data.mappers

import com.example.tmdb_api.domain.models.ResponseLocalUI
import com.example.tmdb_api.domain.models.ResponseRemoteUI
import javax.inject.Inject

class ResponseRemoteUIToLocalUIMapper @Inject constructor() {

    fun mapShowList(responseRemote: List<ResponseRemoteUI>): List<ResponseLocalUI> {
        return responseRemote.map {
            ResponseLocalUI(
                it.id,
                it.imdbID,
                it.title,
                it.overview,
                it.releaseYear,
                it.originalTitle,
                it.genres,
                it.directors,
                it.cast,
                it.rating,
                it.imageSet,
                it.streamingOptions,
                it.seasonCount


            )
        }
    }

    fun mapShow(responseRemote: ResponseRemoteUI): ResponseLocalUI {
        return ResponseLocalUI(
            responseRemote.id,
            responseRemote.imdbID,
            responseRemote.title,
            responseRemote.overview,
            responseRemote.releaseYear,
            responseRemote.originalTitle,
            responseRemote.genres,
            responseRemote.directors,
            responseRemote.cast,
            responseRemote.rating,
            responseRemote.imageSet,
            responseRemote.streamingOptions,
            responseRemote.seasonCount
        )
    }


}