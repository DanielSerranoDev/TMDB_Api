package com.example.tmdb_api.data.mappers

import com.example.tmdb_api.domain.models.ResponseRemoteUI
import com.example.tmdb_api.domain.models.Show
import javax.inject.Inject

class ResponseRemoteShowToUIShowMapper @Inject constructor(){

    fun map(responseRemote: Show): ResponseRemoteUI{
        return ResponseRemoteUI(
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