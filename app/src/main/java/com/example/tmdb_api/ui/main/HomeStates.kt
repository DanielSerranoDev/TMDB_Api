package com.example.tmdb_api.ui.main

import com.example.tmdb_api.data.Remote.ResponseRepository
import com.example.tmdb_api.domain.models.Show


sealed class HomeState {
    object Idle : HomeState()
    data class Success(val data: ResponseRepository) : HomeState()
    data class Error(val error: String) : HomeState()
}



