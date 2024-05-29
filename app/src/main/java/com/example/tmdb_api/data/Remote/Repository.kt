package com.example.tmdb_api.data.Remote

import android.util.Log
import com.example.tmdb_api.data.mappers.ResponseRemoteListToUIListMapper
import com.example.tmdb_api.data.mappers.ResponseRemoteShowToUIShowMapper
import com.example.tmdb_api.domain.models.ResponseRemoteUI
import javax.inject.Inject

data class ResponseRepository(
    val sciFiMovies: List<ResponseRemoteUI>,
    val comedySeries: List<ResponseRemoteUI>,
    val actionMovies: List<ResponseRemoteUI>,
)

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val responseRemoteListToUIListMapper: ResponseRemoteListToUIListMapper,
    private val responseRemoteShowToUIShowMapper: ResponseRemoteShowToUIShowMapper
) {

    private suspend fun getSciFiMovies(): List<ResponseRemoteUI> {
        try {
            Log.d("Repository", "Fetching movies...")
            val responseRemote = remoteDataSource.getSciFiMovies()
            Log.d("Repository", "Movies fetched successfully")
            Log.w("Repository", responseRemote.toString())
            return responseRemoteListToUIListMapper.map(responseRemote)
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching movies", e)
            return emptyList() // Or handle the error as needed
        }
    }

    private suspend fun getComedySeries(): List<ResponseRemoteUI>{
        try {
            Log.d("Repository", "Fetching Series...")
            val responseRemote = remoteDataSource.getComedySeries()
            Log.d("Repository", "Series fetched successfully")
            Log.w("Repository", responseRemote.toString())
            return responseRemoteListToUIListMapper.map(responseRemote)
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching Series", e)
            return emptyList() // Or handle the error as needed
        }
    }

    private suspend fun getActionMovies(): List<ResponseRemoteUI> {
        try {
            Log.d("Repository", "Fetching movies...")
            val responseRemote = remoteDataSource.getActionMovies()
            Log.d("Repository", "Movies fetched successfully")
            Log.w("Repository", responseRemote.toString())


            return responseRemoteListToUIListMapper.map(responseRemote)

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

    suspend fun getShowsById(id: String): ResponseRemoteUI? {
        try {
            Log.d("Repository", "Fetching Series...")
            val responseRemote = remoteDataSource.getShowById(id)
            Log.d("Repository", "Series fetched successfully")
            Log.w("Repository", responseRemote.toString())
            return responseRemoteShowToUIShowMapper.map(responseRemote)
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching Show", e)
            return null // Or handle the error as needed
        }
    }

}