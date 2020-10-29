package com.app.movie.presentation.ui.homepage

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.domain.models.MovieVideos
import com.app.movie.domain.repositoryimpl.MovieRepositoryImpl
import com.app.movie.domain.state.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel @ViewModelInject
constructor(
    private val repository: MovieRepositoryImpl,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val viewModelJob = Job()
    private val _dataStateMovieNowPlaying: MutableLiveData<DataState<MovieNowPlaying>> =
        MutableLiveData()
    val dataStateMovieNowPlaying: LiveData<DataState<MovieNowPlaying>>
        get() = _dataStateMovieNowPlaying
    private val _dataStateMovieVideos: MutableLiveData<DataState<MovieVideos>> = MutableLiveData()
    val dataStateMovieVideos: LiveData<DataState<MovieVideos>>
        get() = _dataStateMovieVideos

    fun getMoviesNowPlaying() {
        viewModelScope.launch {
            repository.getMoviesNowPlaying().onEach {
                _dataStateMovieNowPlaying.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun getMovieVideos(id: Int) {
        viewModelScope.launch {
            repository.getMovieVideos(id).onEach {
                _dataStateMovieVideos.value = it
            }.launchIn(viewModelScope)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}