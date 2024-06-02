package com.example.tmdb_api.ui.main.NewShows

import com.example.tmdb_api.domain.models.ResponseRemoteUI

sealed class NewShowsStates {
    object Idle : NewShowsStates()
    data class Success(val data: List<ResponseRemoteUI>) : NewShowsStates()
    data class Error(val error: String) : NewShowsStates()
}
