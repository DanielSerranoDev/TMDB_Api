package com.example.tmdb_api.data.mappers

import com.example.tmdb_api.domain.models.Change
import com.example.tmdb_api.domain.models.ResponseRemoteUI
import com.example.tmdb_api.domain.models.Show
import javax.inject.Inject

class ResponseRemoteListToUIListMapper @Inject constructor(){

    fun map(responseRemote: List<Show>): List<ResponseRemoteUI>{
        return responseRemote.map {
            ResponseRemoteUI(
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

}

/*
val id: String,
val imdbID: String?,
val title: String?,
val overview: String?,
val releaseYear: Long? = null,
val originalTitle: String?,
val genres: List<Genre>?,
val directors: List<String>? = null,
val cast: List<String>?,
val rating: Long?,
val imageSet: ShowImageSet?,
val streamingOptions: StreamingOptions?,
val creators: List<String>? = null,
val seasonCount: Long? = null,
 */