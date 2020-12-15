package com.app.movie.presentation.ui.homepage

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.domain.repository.MovieRepository
import com.app.movie.domain.repository.TVSeriesRepository
import com.app.movie.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel
@ViewModelInject
constructor(
    private val movieRepository: MovieRepository,
    private val tvSeriesRepository: TVSeriesRepository,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val viewModelJob = Job()
    private val _dataStateMovieNowPlaying: MutableLiveData<List<MovieNowPlayingResultsItem>> =
        MutableLiveData()
    val dataStateMovieNowPlaying: LiveData<List<MovieNowPlayingResultsItem>>
        get() = _dataStateMovieNowPlaying
    private val _dataStateTVSeriesTopRated: MutableLiveData<List<TVSeriesTopRatedResult>> =
        MutableLiveData()
    val dataStateTVSeriesTopRated: LiveData<List<TVSeriesTopRatedResult>>
        get() = _dataStateTVSeriesTopRated

    fun getMoviesNowPlaying(): Flow<PagingData<MovieNowPlayingResultsItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { movieRepository.moviePlayingNowPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getFavMoviesNowPlaying() {
        viewModelScope.launch {
            _dataStateMovieNowPlaying.value = movieRepository.getFavMoviesNowPlaying()
        }
    }

    fun insertFavMoviesNowPlaying(movieNowPlayingResultsItem: MovieNowPlayingResultsItem) {
        viewModelScope.launch {
            movieRepository.insertFavMoviesNowPlaying(movieNowPlayingResultsItem)
        }
    }

    fun deleteFavMoviesNowPlaying(movieNowPlayingResultsItem: MovieNowPlayingResultsItem) {
        viewModelScope.launch {
            movieRepository.deleteFavMoviesNowPlaying(movieNowPlayingResultsItem)
        }
    }

    fun getTVSeriesTopRated(): Flow<PagingData<TVSeriesTopRatedResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { tvSeriesRepository.tvSeriesTopRatedPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getFavTVSeriesTopRated() {
        viewModelScope.launch {
            _dataStateTVSeriesTopRated.value = tvSeriesRepository.getFavTVTopRated()
        }
    }

    fun insertFavTVSeriesTopRated(tvSeriesTopRatedResultsItem: TVSeriesTopRatedResult) {
        viewModelScope.launch {
            tvSeriesRepository.insertFavTVTopRated(tvSeriesTopRatedResultsItem)
        }
    }

    fun deleteFavTVSeriesTopRated(tvSeriesTopRatedResultsItem: TVSeriesTopRatedResult) {
        viewModelScope.launch {
            tvSeriesRepository.deleteFavTVTopRated(tvSeriesTopRatedResultsItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}