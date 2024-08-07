package com.example.tmdb_api.ui.main.Detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_api.data.Remote.Repository
import com.example.tmdb_api.domain.models.ResponseLocalUI
import com.example.tmdb_api.domain.models.ResponseRemoteUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
    @Inject
    constructor(
        private val repository: Repository,
    ) : ViewModel() {
        private val _state: MutableStateFlow<DetailState> = MutableStateFlow(DetailState.Idle)
        val state: StateFlow<DetailState> = _state.asStateFlow()

        fun getDetail(id: String) {
            viewModelScope.launch {
                _state.update { DetailState.Idle }

                val show =
                    runCatching {
                        withContext(Dispatchers.IO) {
                            repository.getShowsIdRm(id)
                        }
                    }
                if (show.isSuccess) {
                    _state.update { DetailState.Success(show.getOrThrow()) }
                } else {
                    _state.update { DetailState.Error(show.exceptionOrNull()?.message.orEmpty()) }
                }
            }
        }

        fun insertShow(responseRepository: ResponseRemoteUI?) {
            viewModelScope.launch {
                val showDB =
                    withContext(Dispatchers.IO) {
                        repository.insertShow(responseRepository!!)
                    }
            }
        }

        suspend fun getShowById(id: String): ResponseLocalUI =
            withContext(Dispatchers.IO) {
                repository.getShowIdDB(id)
            }

        fun deleteShow(id: String) {
            viewModelScope.launch {
                val showDB =
                    withContext(Dispatchers.IO) {
                        repository.deleteShow(id)
                    }
            }
        }
    }
