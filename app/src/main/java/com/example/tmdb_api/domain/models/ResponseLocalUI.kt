package com.example.tmdb_api.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "showDB")
data class ResponseLocalUI (
    @PrimaryKey
    @ColumnInfo(name = "id")val id: String,
    @ColumnInfo(name = "imdbID")val imdbID: String?,
    @ColumnInfo(name = "title")val title: String?,
    @ColumnInfo(name = "overview")val overview: String?,
    @ColumnInfo(name = "releaseYear")val releaseYear: Long? = null,
    @ColumnInfo(name = "originalTitle")val originalTitle: String?,
    @ColumnInfo(name = "genres")val genres: List<Genre>? = null,
    @ColumnInfo(name = "directors")val directors: List<String>? = null,
    @ColumnInfo(name = "cast")val cast: List<String>?,
    @ColumnInfo(name = "rating")val rating: Long?,
    @ColumnInfo(name = "imageSet")val imageSet: ShowImageSet?,
    @ColumnInfo(name = "streamingOptions")val streamingOptions: StreamingOptions,
    @ColumnInfo(name = "seasonCount")val seasonCount: Long? = null,
    @ColumnInfo(name = "isFavourite") val isFavourite: Boolean = false
) 