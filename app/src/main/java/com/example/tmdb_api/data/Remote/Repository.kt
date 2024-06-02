package com.example.tmdb_api.data.Remote

import android.util.Log
import com.example.tmdb_api.data.Local.LocalDataSourceInterface
import com.example.tmdb_api.data.mappers.ResponseLocalUIToRemoteUIMapper
import com.example.tmdb_api.data.mappers.ResponseRemoteListToUIListMapper
import com.example.tmdb_api.data.mappers.ResponseRemoteShowToUIShowMapper
import com.example.tmdb_api.data.mappers.ResponseRemoteUIToLocalUIMapper
import com.example.tmdb_api.domain.models.ResponseRemoteUI
import javax.inject.Inject

data class ResponseRepositoryByGender(
    val sciFiMovies: List<ResponseRemoteUI>,
    val comedySeries: List<ResponseRemoteUI>,
    val actionMovies: List<ResponseRemoteUI>,
)

data class ResponseRepositoryByCatalog(
    val netflixRatings: List<ResponseRemoteUI>,
    val amazonPrimeRatings: List<ResponseRemoteUI>,
    val hboMaxRatings: List<ResponseRemoteUI>,
)

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val responseRemoteListToUIListMapper: ResponseRemoteListToUIListMapper,
    private val responseRemoteShowToUIShowMapper: ResponseRemoteShowToUIShowMapper,
    private val responseRemoteUIToLocalUIMapper: ResponseRemoteUIToLocalUIMapper,
    private val responseLocalUIToRemoteUIMapper: ResponseLocalUIToRemoteUIMapper,
    private val localDataSourceInterface: LocalDataSourceInterface
) {

    private suspend fun getNetflixRatings(): List<ResponseRemoteUI> {
        try {
            Log.d("Repository", "Fetching movies/series...")
            val responseRemote = remoteDataSource.getNetflixRatings()
            Log.d("Repository", "Movies/Series fetched successfully")
            Log.w("Repository", responseRemote.toString())
            val convertResponseToRemoteUI = responseRemoteListToUIListMapper.map(responseRemote)
            /*
            //Convert to local UI
            val convertRemoteUIToLocalClass = responseRemoteUIToLocalUIMapper.mapShowList(convertResponseToRemoteUI)

            //Consultar DB
            val localData = localDataSourceInterface.getAllShows()

            if (localData.isEmpty()) {
                //Save to DB
                localDataSourceInterface.insertAllShows(convertRemoteUIToLocalClass)
            }
             */
            return convertResponseToRemoteUI
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching movies", e)
            return emptyList() // Or handle the error as needed
        }
    }

    private suspend fun getAmazonPrimeRatings(): List<ResponseRemoteUI> {
        try {
            Log.d("Repository", "Fetching movies/series...")
            val responseRemote = remoteDataSource.getAmazonPrimeRatings()
            Log.d("Repository", "Movies/Series fetched successfully")
            Log.w("Repository", responseRemote.toString())
            val convertResponseToRemoteUI = responseRemoteListToUIListMapper.map(responseRemote)
            /*
            //Convert to local UI
            val convertRemoteUIToLocalClass = responseRemoteUIToLocalUIMapper.mapShowList(convertResponseToRemoteUI)

            //Consultar DB
            val localData = localDataSourceInterface.getAllShows()

            if (localData.isEmpty()) {
                //Save to DB
                localDataSourceInterface.insertAllShows(convertRemoteUIToLocalClass)
            }
             */
            return convertResponseToRemoteUI
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching movies", e)
            return emptyList() // Or handle the error as needed
        }
    }

    private suspend fun getHboMaxRatings(): List<ResponseRemoteUI> {
        try {
            Log.d("Repository", "Fetching movies/series...")
            val responseRemote = remoteDataSource.getHboMaxRatings()
            Log.d("Repository", "Movies/Series fetched successfully")
            Log.w("Repository", responseRemote.toString())
            val convertResponseToRemoteUI = responseRemoteListToUIListMapper.map(responseRemote)
            /*
            //Convert to local UI
            val convertRemoteUIToLocalClass = responseRemoteUIToLocalUIMapper.mapShowList(convertResponseToRemoteUI)

            //Consultar DB
            val localData = localDataSourceInterface.getAllShows()

            if (localData.isEmpty()) {
                //Save to DB
                localDataSourceInterface.insertAllShows(convertRemoteUIToLocalClass)
            }
             */
            return convertResponseToRemoteUI
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching movies", e)
            return emptyList() // Or handle the error as needed
        }
    }

    /*
    private suspend fun getSciFiMovies(): List<ResponseRemoteUI> {
        try {

            Log.d("Repository", "Fetching movies...")
            val responseRemote = remoteDataSource.getSciFiMovies()
            Log.d("Repository", "Movies fetched successfully")
            Log.w("Repository", responseRemote.toString())
            val convertResponseToRemoteUI = responseRemoteListToUIListMapper.map(responseRemote)
            /*
            //Convert to local UI
            val convertRemoteUIToLocalClass = responseRemoteUIToLocalUIMapper.mapShowList(convertResponseToRemoteUI)
            //Consultar DB
            val localData = localDataSourceInterface.getAllShows()

            if (localData.isEmpty()) {
                //Save to DB
                localDataSourceInterface.insertAllShows(convertRemoteUIToLocalClass)
            }
             */
            return convertResponseToRemoteUI
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching movies", e)
            return emptyList() // Or handle the error as needed
        }
    }

    private suspend fun getComedySeries(): List<ResponseRemoteUI> {
        try {

            Log.d("Repository", "Fetching movies...")
            val responseRemote = remoteDataSource.getComedySeries()
            Log.d("Repository", "Movies fetched successfully")
            Log.w("Repository", responseRemote.toString())
            val convertResponseToRemoteUI = responseRemoteListToUIListMapper.map(responseRemote)
            /*
            //Convert to local UI
            val convertRemoteUIToLocalClass = responseRemoteUIToLocalUIMapper.mapShowList(convertResponseToRemoteUI)
            //Consultar DB
            val localData = localDataSourceInterface.getAllShows()
            if (localData.isEmpty()) {
                //Save to DB
                localDataSourceInterface.insertAllShows(convertRemoteUIToLocalClass)
            }
             */
            return convertResponseToRemoteUI


        } catch (e: Exception) {
            Log.e("Repository", "Error fetching movies", e)
            return emptyList() // Or handle the error as needed
        }
    }

    private suspend fun getActionMovies(): List<ResponseRemoteUI> {
        try {

            Log.d("Repository", "Fetching movies...")
            val responseRemote = remoteDataSource.getActionMovies()
            Log.d("Repository", "Movies fetched successfully")
            Log.w("Repository", responseRemote.toString())
            val convertResponseToRemoteUI = responseRemoteListToUIListMapper.map(responseRemote)
            //Convert to local UI
            val convertRemoteUIToLocalClass = responseRemoteUIToLocalUIMapper.mapShowList(convertResponseToRemoteUI)
            /*
            //Consultar DB
            val localData = localDataSourceInterface.getAllShows()
            if (localData.isEmpty()) {
                //Save to DB
                localDataSourceInterface.insertAllShows(convertRemoteUIToLocalClass)
            }
             */
            return convertResponseToRemoteUI

        } catch (e: Exception) {
            Log.e("Repository", "Error fetching movies", e)
            return emptyList() // Or handle the error as needed
        }
    }


     */
    suspend fun getNewChange(): List<ResponseRemoteUI>{
        val responseRemote = remoteDataSource.getNewChange().toList()
        Log.d("Repository", "Movies/Series updated fetched successfully")
        Log.w("Repository", responseRemote.toString())
        return responseRemoteListToUIListMapper.map(responseRemote)
    }

    suspend fun repositoryResponseByCatalog(): ResponseRepositoryByCatalog {

        val netflixRatings = getNetflixRatings()
        val amazonPrimeRatings = getAmazonPrimeRatings()
        val hboMaxRatings = getHboMaxRatings()

        return ResponseRepositoryByCatalog(netflixRatings, amazonPrimeRatings, hboMaxRatings)
    }

    suspend fun getShowsById(id: String): ResponseRemoteUI? {
        try {
            //Consulta DB
            val responseLocal = localDataSourceInterface.getShowById(id)
            Log.w("Repository Local", "id: $id")

            //Devuelve dependiendo si la base de datos esta vacia
            return if (responseLocal != null) {
                Log.w("Repository Local", "Hay datos en DB: $responseLocal.toString()")
                val convertToLocalToRemoteUI =
                    responseLocalUIToRemoteUIMapper.mapShow(responseLocal)

                convertToLocalToRemoteUI
            } else {
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