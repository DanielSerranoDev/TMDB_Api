package com.example.tmdb_api.domain.models

data class ResponseRemoteUI(
    val id: String,
    val imdbID: String?,
    val title: String?,
    val overview: String?,
    val releaseYear: Long? = null,
    val originalTitle: String?,
    val genres: List<Genre>? = null,
    val directors: List<String>? = null,
    val cast: List<String>?,
    val rating: Long?,
    val imageSet: ShowImageSet?,
    val streamingOptions: StreamingOptions,
    val seasonCount: Long? = null,
)
