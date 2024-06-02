package com.example.tmdb_api.ui.main.Home.Detail

import com.example.tmdb_api.domain.models.ResponseRemoteUI

sealed class DetailState {
    object Idle : DetailState()
    data class Success(val data: ResponseRemoteUI?) : DetailState()
    data class Error(val error: String) : DetailState()
}