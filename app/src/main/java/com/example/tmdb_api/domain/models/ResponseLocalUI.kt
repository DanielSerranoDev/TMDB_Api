package com.example.tmdb_api.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "showDB")
data class ResponseLocalUI (
    @PrimaryKey
    @ColumnInfo(name = "id")val id: String,
    @ColumnInfo(name = "title")val title: String?,
    @ColumnInfo(name = "isFavourite") val isFavourite: Boolean = true
) 