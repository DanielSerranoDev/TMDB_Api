package com.example.tmdb_api.data.Remote

import android.util.Log
import com.example.tmdb_api.data.Local.LocalDataSourceInterface
import com.example.tmdb_api.data.mappers.ResponseLocalUIToRemoteUIMapper
import com.example.tmdb_api.data.mappers.ResponseRemoteListToUIListMapper
import com.example.tmdb_api.data.mappers.ResponseRemoteShowToUIShowMapper
import com.example.tmdb_api.data.mappers.ResponseRemoteUIToLocalUIMapper
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
    private val responseRemoteShowToUIShowMapper: ResponseRemoteShowToUIShowMapper,
    private val responseRemoteUIToLocalUIMapper: ResponseRemoteUIToLocalUIMapper,
    private val responseLocalUIToRemoteUIMapper: ResponseLocalUIToRemoteUIMapper,
    private val localDataSourceInterface: LocalDataSourceInterface
) {

    private suspend fun getSciFiMovies(): List<ResponseRemoteUI> {
        try {

            val responseLocal = localDataSourceInterface.getShowByGenre("scifi")

            return if (responseLocal.isNotEmpty()) {
                 val responseLocal = responseLocalUIToRemoteUIMapper.mapShowList(responseLocal)
                Log.w("Repository Local", responseLocal.toString())
                responseLocal
            }else{
                Log.d("Repository", "Fetching movies...")
                val responseRemote = remoteDataSource.getSciFiMovies()
                Log.d("Repository", "Movies fetched successfully")
                Log.w("Repository", responseRemote.toString())
                val convertToRemoteUI = responseRemoteListToUIListMapper.map(responseRemote)
                //Convert to local UI
                val convertToLocalClass = responseRemoteUIToLocalUIMapper.mapShowList(convertToRemoteUI)
                //Save to DB
                localDataSourceInterface.insertAllShows(convertToLocalClass)
                convertToRemoteUI
            }

        } catch (e: Exception) {
            Log.e("Repository", "Error fetching movies", e)
            return emptyList() // Or handle the error as needed
        }
    }

    private suspend fun getComedySeries(): List<ResponseRemoteUI>{
        try {

            val responseLocal = localDataSourceInterface.getShowByGenre("comedy")

            return if (responseLocal.isNotEmpty()) {
                val responseLocal = responseLocalUIToRemoteUIMapper.mapShowList(responseLocal)
                Log.w("Repository Local", responseLocal.toString())
                responseLocal
            }else{
                Log.d("Repository", "Fetching movies...")
                val responseRemote = remoteDataSource.getComedySeries()
                Log.d("Repository", "Movies fetched successfully")
                Log.w("Repository", responseRemote.toString())
                val convertToRemoteUI = responseRemoteListToUIListMapper.map(responseRemote)
                //Convert to local UI
                val convertToLocalClass = responseRemoteUIToLocalUIMapper.mapShowList(convertToRemoteUI)
                //Save to DB
                localDataSourceInterface.insertAllShows(convertToLocalClass)
                convertToRemoteUI
            }

        } catch (e: Exception) {
            Log.e("Repository", "Error fetching movies", e)
            return emptyList() // Or handle the error as needed
        }
    }

    private suspend fun getActionMovies(): List<ResponseRemoteUI> {
        try {

            val responseLocal = localDataSourceInterface.getShowByGenre("action")

            return if (responseLocal.isNotEmpty()) {
                val responseLocal = responseLocalUIToRemoteUIMapper.mapShowList(responseLocal)
                Log.w("Repository Local", responseLocal.toString())
                responseLocal
            }else{
                Log.d("Repository", "Fetching movies...")
                val responseRemote = remoteDataSource.getActionMovies()
                Log.d("Repository", "Movies fetched successfully")
                Log.w("Repository", responseRemote.toString())
                val convertResponseToRemoteUI = responseRemoteListToUIListMapper.map(responseRemote)
                //Convert to local UI
                val convertRemoteUIToLocalClass = responseRemoteUIToLocalUIMapper.mapShowList(convertResponseToRemoteUI)
                //Save to DB
                localDataSourceInterface.insertAllShows(convertRemoteUIToLocalClass)
                convertResponseToRemoteUI
            }

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
            //Consulta DB
            val responseLocal = localDataSourceInterface.getShowById(id)
            Log.w("Repository Local", responseLocal.toString())

            //Devuelve dependiendo si la base de datos esta vacia
            return if(responseLocal != null){

                val convertToLocalToRemoteUI = responseLocalUIToRemoteUIMapper.mapShow(responseLocal)

                convertToLocalToRemoteUI
            }else {
                Log.d("Repository", "Fetching Series...")
                val responseRemote = remoteDataSource.getShowById(id)
                Log.d("Repository", "Series fetched successfully")
                Log.w("Repository", responseRemote.toString())
                responseRemoteShowToUIShowMapper.map(responseRemote)

            }
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching Show", e)
            return null // Or handle the error as needed
        }
    }

}