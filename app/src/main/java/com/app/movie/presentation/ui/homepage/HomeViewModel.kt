package com.app.movie.presentation.ui.homepage

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.domain.models.TVSeriesTopRated
import com.app.movie.domain.repositoryimpl.MovieRepositoryImpl
import com.app.movie.domain.repositoryimpl.TVSeriesRepositoryImpl
import com.app.movie.domain.state.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel @ViewModelInject
constructor(
    private val movieRepository: MovieRepositoryImpl,
    private val tvSeriesRepository: TVSeriesRepositoryImpl,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val viewModelJob = Job()
    private val _dataStateMovieNowPlaying: MutableLiveData<DataState<MovieNowPlaying>> =
        MutableLiveData()
    val dataStateMovieNowPlaying: LiveData<DataState<MovieNowPlaying>>
        get() = _dataStateMovieNowPlaying
    private val _dataStateTVSeriesTopRated: MutableLiveData<DataState<TVSeriesTopRated>> =
        MutableLiveData()
    val dataStateTVSeriesTopRated: LiveData<DataState<TVSeriesTopRated>>
        get() = _dataStateTVSeriesTopRated

    fun getMoviesNowPlaying() {
        viewModelScope.launch {
            movieRepository.getMoviesNowPlaying().onEach {
                _dataStateMovieNowPlaying.value = it as DataState<MovieNowPlaying>
            }.launchIn(viewModelScope)
        }
    }

    fun getTVSeriesTopRated() {
        viewModelScope.launch {
            tvSeriesRepository.geTVSeriesTopRated().onEach {
                _dataStateTVSeriesTopRated.value = it as DataState<TVSeriesTopRated>
            }.launchIn(viewModelScope)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}