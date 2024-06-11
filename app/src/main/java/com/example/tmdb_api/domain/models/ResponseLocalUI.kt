package com.example.tmdb_api.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "showDB")
data class ResponseLocalUI (
    @PrimaryKey
    @ColumnInfo(name = "id")val id: String,
    @ColumnInfo(name = "title")val title: String?,
    @ColumnInfo(name = "imageSet")val imageSet: ShowImageSet?,
)

/*
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
 */