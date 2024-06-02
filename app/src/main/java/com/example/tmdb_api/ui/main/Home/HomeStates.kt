package com.example.tmdb_api.ui.main.Home

import com.example.tmdb_api.data.Remote.ResponseRepositoryByCatalog


sealed class HomeState {
    object Idle : HomeState()
    data class Success(val data: ResponseRepositoryByCatalog) : HomeState()
    data class Error(val error: String) : HomeState()
}



