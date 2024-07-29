package com.example.tmdb_api.data.Local

import androidx.room.TypeConverter
import com.example.tmdb_api.domain.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromGenreList(genres: List<Genre>?): String? = Gson().toJson(genres)

    @TypeConverter
    fun toGenreList(genresString: String?): List<Genre>? = Gson().fromJson(genresString, object : TypeToken<List<Genre>>() {}.type)

    @TypeConverter
    fun fromStringList(strings: List<String>?): String? = Gson().toJson(strings)

    @TypeConverter
    fun toStringList(strings: String?): List<String>? = Gson().fromJson(strings, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun fromShowImageSet(imageSet: ShowImageSet?): String? = Gson().toJson(imageSet)

    @TypeConverter
    fun toShowImageSet(imageSetString: String?): ShowImageSet? = Gson().fromJson(imageSetString, object : TypeToken<ShowImageSet>() {}.type)

    @TypeConverter
    fun fromStreamingOptions(streamingOptions: StreamingOptions?): String? = Gson().toJson(streamingOptions)

    @TypeConverter
    fun toStreamingOptions(streamingOptionsString: String?): StreamingOptions? =
        Gson().fromJson(streamingOptionsString, object : TypeToken<StreamingOptions>() {}.type)

    @TypeConverter
    fun fromGenre(genre: Genre?): String? = Gson().toJson(genre)

    @TypeConverter
    fun toGenre(genreString: String?): Genre? = Gson().fromJson(genreString, object : TypeToken<Genre>() {}.type)
}
