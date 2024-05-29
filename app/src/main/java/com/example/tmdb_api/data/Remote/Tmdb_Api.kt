package com.example.tmdb_api.data.Remote

import com.example.tmdb_api.domain.models.APIResponse
import com.example.tmdb_api.domain.models.Show
import retrofit2.http.GET
import retrofit2.http.Path

interface Tmdb_Api {

    @GET("shows/search/filters?country=us&show_type=movie&genres=scifi&series_granularity=show&order_by=original_title&output_language=en&order_direction=asc&genres_relation=and&year_min=2020")
    suspend fun getScifiMovies(): APIResponse

    @GET("shows/search/filters?country=us&show_type=series&genres=comedy&series_granularity=show&order_by=original_title&output_language=en&order_direction=asc&genres_relation=and&year_min=2020")
    suspend fun getComedySeries(): APIResponse

    @GET("shows/search/filters?country=us&show_type=movie&genres=action&series_granularity=show&order_by=original_title&output_language=en&order_direction=asc&genres_relation=and&year_min=2020")
    suspend fun getActionMovies(): APIResponse

    //https://streaming-availability.p.rapidapi.com/shows/5460092?output_language=en

    @GET("shows/{id}?output_language=en")
    suspend fun getShowDetails(
        @Path("id") id: String
    ): Show


}
