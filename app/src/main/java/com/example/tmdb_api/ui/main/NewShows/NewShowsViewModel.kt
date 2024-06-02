package com.example.tmdb_api.ui.main.NewShows

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
class NewShowsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _state: MutableStateFlow<NewShowsStates> = MutableStateFlow(NewShowsStates.Idle)
    val state: StateFlow<NewShowsStates> = _state.asStateFlow()

    fun getRepositoryData(){

        viewModelScope.launch {
            _state.update { NewShowsStates.Idle }

            val user = runCatching {
                withContext(Dispatchers.IO) {

                    repository.getNewChange()

                }
            }
            if (user.isSuccess) {
                _state.update { NewShowsStates.Success(user.getOrThrow()) }
            } else {
                _state.update { NewShowsStates.Error(user.exceptionOrNull()?.message.orEmpty()) }
            }
        }
    }




}