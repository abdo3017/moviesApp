package com.app.movie.presentation.ui.movies

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.domain.models.MoviePopular
import com.app.movie.domain.models.MovieTopRated
import com.app.movie.domain.models.MovieUpComing
import com.app.movie.domain.repositoryimpl.MovieRepositoryImpl
import com.app.movie.domain.state.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MoviesViewModel
@ViewModelInject
constructor(
    private val movieRepository: MovieRepositoryImpl,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val viewModelJob = Job()
    private val _dataStateMovieNowPlaying: MutableLiveData<DataState<MovieNowPlaying>> =
        MutableLiveData()
    val dataStateMovieNowPlaying: LiveData<DataState<MovieNowPlaying>>
        get() = _dataStateMovieNowPlaying

    private val _dataStateMovieUpComing: MutableLiveData<DataState<MovieUpComing>> =
        MutableLiveData()
    val dataStateMovieUpComing: LiveData<DataState<MovieUpComing>>
        get() = _dataStateMovieUpComing

    private val _dataStateMovieTopRated: MutableLiveData<DataState<MovieTopRated>> =
        MutableLiveData()
    val dataStateMovieTopRated: LiveData<DataState<MovieTopRated>>
        get() = _dataStateMovieTopRated

    private val _dataStateMoviePopular: MutableLiveData<DataState<MoviePopular>> =
        MutableLiveData()
    val dataStateMoviePopular: LiveData<DataState<MoviePopular>>
        get() = _dataStateMoviePopular

    fun getMoviesNowPlaying() {
        viewModelScope.launch {
            movieRepository.getMoviesNowPlaying().onEach {
                _dataStateMovieNowPlaying.value = it as DataState<MovieNowPlaying>
            }.launchIn(viewModelScope)
        }
    }

    fun getMoviesUpComing() {
        viewModelScope.launch {
            movieRepository.getMoviesUpComing().onEach {
                _dataStateMovieUpComing.value = it as DataState<MovieUpComing>
            }.launchIn(viewModelScope)
        }
    }

    fun getMoviesPopular() {
        viewModelScope.launch {
            movieRepository.getMoviesPopular().onEach {
                _dataStateMoviePopular.value = it as DataState<MoviePopular>
            }.launchIn(viewModelScope)
        }
    }

    fun getMoviesTopRated() {
        viewModelScope.launch {
            movieRepository.getMoviesTopRated().onEach {
                _dataStateMovieTopRated.value = it as DataState<MovieTopRated>
            }.launchIn(viewModelScope)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}