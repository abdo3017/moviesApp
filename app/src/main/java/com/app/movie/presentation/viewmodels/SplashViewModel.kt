package com.app.movie.presentation.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.domain.repositoryimpl.MovieRepositoryImpl
import com.app.movie.domain.state.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SplashViewModel
@ViewModelInject
constructor(
    private val repository: MovieRepositoryImpl,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val viewModelJob = Job()
    private val _dataState: MutableLiveData<DataState<MovieNowPlaying>> = MutableLiveData()
    val dataState: LiveData<DataState<MovieNowPlaying>>
        get() = _dataState

    fun getMoviesNowPlaying(){
        viewModelScope.launch{
            repository.getMoviesNowPlaying().onEach {
                _dataState.value=it
            }.launchIn(viewModelScope)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}