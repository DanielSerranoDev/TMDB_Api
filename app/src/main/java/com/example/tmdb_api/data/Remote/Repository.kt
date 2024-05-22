package com.example.tmdb_api.data.Remote

import android.util.Log
import com.example.tmdb_api.domain.models.Show
import javax.inject.Inject

data class ResponseRepository(
    val sciFiMovies: List<Show>,
    val comedySeries: List<Show>,
    val actionMovies: List<Show>,
)

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getSciFiMovies(): List<Show> {
        try {
            Log.d("Repository", "Fetching movies...")
            val responseRemote = remoteDataSource.getSciFiMovies()
            Log.d("Repository", "Movies fetched successfully")
            Log.w("Repository", responseRemote.toString())
            return responseRemote
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching movies", e)
            return emptyList() // Or handle the error as needed
        }
    }

    suspend fun getComedySeries(): List<Show>{
        try {
            Log.d("Repository", "Fetching Series...")
            val responseRemote = remoteDataSource.getComedySeries()
            Log.d("Repository", "Series fetched successfully")
            Log.w("Repository", responseRemote.toString())
            return responseRemote
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching Series", e)
            return emptyList() // Or handle the error as needed
        }
    }

    suspend fun getActionMovies(): List<Show> {
        try {
            Log.d("Repository", "Fetching movies...")
            val responseRemote = remoteDataSource.getActionMovies()
            Log.d("Repository", "Movies fetched successfully")
            Log.w("Repository", responseRemote.toString())
            return responseRemote
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching movies", e)
            return emptyList() // Or handle the error as needed
        }
    }

    suspend fun repositoryResponse(): ResponseRepository{
        val scifiMovies = getSciFiMovies()
        val comedySeries = getComedySeries()
        val actionMovies = getActionMovies()

        return ResponseRepository(scifiMovies , comedySeries, actionMovies)
    }

}