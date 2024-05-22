package com.example.tmdb_api.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_api.data.Remote.Repository
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
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Idle)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun getRespositoryData(){

        viewModelScope.launch {
            _state.update { HomeState.Idle }

            val user = runCatching {
                withContext(Dispatchers.IO) {

                    repository.repositoryResponse()

                }
            }
            if (user.isSuccess) {
                _state.update { HomeState.Success(user.getOrThrow()) }
            } else {
                _state.update { HomeState.Error(user.exceptionOrNull()?.message.orEmpty()) }
            }
        }
    }




}