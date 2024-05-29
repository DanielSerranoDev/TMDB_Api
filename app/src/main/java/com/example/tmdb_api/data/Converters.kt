package com.example.tmdb_api.data.Local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.tmdb_api.domain.models.*

class Converters {

    @TypeConverter
    fun fromGenreList(genres: List<Genre>?): String? {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toGenreList(genresString: String?): List<Genre>? {
        return Gson().fromJson(genresString, object : TypeToken<List<Genre>>() {}.type)
    }

    @TypeConverter
    fun fromStringList(strings: List<String>?): String? {
        return Gson().toJson(strings)
    }

    @TypeConverter
    fun toStringList(strings: String?): List<String>? {
        return Gson().fromJson(strings, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun fromShowImageSet(imageSet: ShowImageSet?): String? {
        return Gson().toJson(imageSet)
    }

    @TypeConverter
    fun toShowImageSet(imageSetString: String?): ShowImageSet? {
        return Gson().fromJson(imageSetString, object : TypeToken<ShowImageSet>() {}.type)
    }

    @TypeConverter
    fun fromStreamingOptions(streamingOptions: StreamingOptions?): String? {
        return Gson().toJson(streamingOptions)
    }

    @TypeConverter
    fun toStreamingOptions(streamingOptionsString: String?): StreamingOptions? {
        return Gson().fromJson(streamingOptionsString, object : TypeToken<StreamingOptions>() {}.type)
    }

    @TypeConverter
    fun fromGenre(genre: Genre?): String? {
        return Gson().toJson(genre)
    }

    @TypeConverter
    fun toGenre(genreString: String?): Genre? {
        return Gson().fromJson(genreString, object : TypeToken<Genre>() {}.type)
    }
}
