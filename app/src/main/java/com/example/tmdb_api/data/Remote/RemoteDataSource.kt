package com.example.tmdb_api.data.Remote

import android.util.Log
import com.example.tmdb_api.domain.models.Show
import javax.inject.Inject

class RemoteDataSource@Inject constructor(private val api: Tmdb_Api) {

    suspend fun getSciFiMovies(): List<Show> {
        val responseApi = api.getScifiMovies()
        Log.w("TAG", "getMovies: $responseApi")
        val response = responseApi.shows
        return response
    }

    suspend fun getComedySeries(): List<Show> {
        val responseApi = api.getComedySeries()
        Log.w("TAG", "getSeries: $responseApi")
        val response = responseApi.shows
        return response
    }

    suspend fun getActionMovies(): List<Show> {
        val responseApi = api.getActionMovies()
        Log.w("TAG", "getActionMovies: $responseApi")
        val response = responseApi.shows
        return response
    }

}