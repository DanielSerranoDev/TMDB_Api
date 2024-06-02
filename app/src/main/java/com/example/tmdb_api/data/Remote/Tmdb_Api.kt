package com.example.tmdb_api.data.Remote

import com.example.tmdb_api.domain.models.APIResponse
import com.example.tmdb_api.domain.models.APIResponseChanges
import com.example.tmdb_api.domain.models.Show
import retrofit2.http.GET
import retrofit2.http.Path

interface Tmdb_Api {


    //Netflix-rating descending
    @GET("shows/search/filters?country=us&series_granularity=episode&order_direction=desc&order_by=original_title&genres_relation=and&output_language=en&catalogs=netflix")
    suspend fun getNetflixRatings(): APIResponse

    //Amazon Prime-rating descending
    @GET("shows/search/filters?country=us&series_granularity=episode&order_direction=desc&order_by=original_title&genres_relation=and&output_language=en&catalogs=prime.subscription")
    suspend fun getAmazonPrimRatings(): APIResponse

    //Hbo Max-rating descending
    @GET("shows/search/filters?country=us&series_granularity=episode&order_direction=desc&order_by=original_title&genres_relation=and&output_language=en&catalogs=hbo%2Chulu.addon.hbo%2Cprime.addon.hbomaxus")
    suspend fun getHboMaxRatings(): APIResponse

    @GET("changes?change_type=new&country=us&item_type=show&output_language=en&order_direction=asc&include_unknown_dates=false&catalogs=prime.subscription%2Cnetflix%2Chbo%2Chulu.addon.hbo%2Cprime.addon.hbomaxus")
    suspend fun getNewChanges(): APIResponseChanges

    /* Calls by genre
        @GET("shows/search/filters?country=us&show_type=movie&genres=scifi&series_granularity=show&order_by=original_title&output_language=en&order_direction=asc&genres_relation=and&year_min=2020")
    suspend fun getScifiMovies(): APIResponse

    @GET("shows/search/filters?country=us&show_type=series&genres=comedy&series_granularity=show&order_by=original_title&output_language=en&order_direction=asc&genres_relation=and&year_min=2020")
    suspend fun getComedySeries(): APIResponse

    @GET("shows/search/filters?country=us&show_type=movie&genres=action&series_granularity=show&order_by=original_title&output_language=en&order_direction=asc&genres_relation=and&year_min=2020")
    suspend fun getActionMovies(): APIResponse
     */


    @GET("shows/{id}?output_language=en")
    suspend fun getShowDetails(
        @Path("id") id: String
    ): Show


}
