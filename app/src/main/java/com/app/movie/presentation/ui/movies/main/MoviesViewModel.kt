package com.app.movie.presentation.ui.movies.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
import com.app.movie.datasource.network.models.movies.MoviePopularResult
import com.app.movie.datasource.network.models.movies.MovieTopRatedResult
import com.app.movie.datasource.network.models.movies.MovieUpComingResult
import com.app.movie.domain.repository.MovieRepository
import com.app.movie.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MoviesViewModel
@ViewModelInject
constructor(
    private val movieRepository: MovieRepository,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataStateMovieNowPlaying: MutableLiveData<List<MovieNowPlayingResultsItem>> =
        MutableLiveData()
    val dataStateMovieNowPlaying: LiveData<List<MovieNowPlayingResultsItem>>
        get() = _dataStateMovieNowPlaying
    private val _dataStateMovieTopRated: MutableLiveData<List<MovieTopRatedResult>> =
        MutableLiveData()
    val dataStateMovieTopRated: LiveData<List<MovieTopRatedResult>>
        get() = _dataStateMovieTopRated
    private val _dataStateMoviePopular: MutableLiveData<List<MoviePopularResult>> =
        MutableLiveData()
    val dataStateMoviePopular: LiveData<List<MoviePopularResult>>
        get() = _dataStateMoviePopular
    private val _dataStateMovieUpComing: MutableLiveData<List<MovieUpComingResult>> =
        MutableLiveData()
    val dataStateMovieUpComing: LiveData<List<MovieUpComingResult>>
        get() = _dataStateMovieUpComing

    fun getMoviesNowPlaying(): Flow<PagingData<MovieNowPlayingResultsItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { movieRepository.moviePlayingNowPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getMoviesUpComing(): Flow<PagingData<MovieUpComingResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { movieRepository.movieUpComingPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getMoviesPopular(): Flow<PagingData<MoviePopularResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { movieRepository.moviePopularPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getMoviesTopRated(): Flow<PagingData<MovieTopRatedResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { movieRepository.movieTopRatedPagingSource }
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

    fun getFavMoviesTopRated() {
        viewModelScope.launch {
            _dataStateMovieTopRated.value = movieRepository.getFavMoviesTopRated()
        }
    }

    fun insertFavMoviesTopRated(movieTopRatedResultsItem: MovieTopRatedResult) {
        viewModelScope.launch {
            movieRepository.insertFavMoviesTopRated(movieTopRatedResultsItem)
        }
    }

    fun deleteFavMoviesTopRated(movieTopRatedResultsItem: MovieTopRatedResult) {
        viewModelScope.launch {
            movieRepository.deleteFavMoviesTopRated(movieTopRatedResultsItem)
        }
    }

    fun getFavMoviesPopular() {
        viewModelScope.launch {
            _dataStateMoviePopular.value = movieRepository.getFavMoviesPopular()
        }
    }

    fun insertFavMoviesPopular(moviePopularResultsItem: MoviePopularResult) {
        viewModelScope.launch {
            movieRepository.insertFavMoviesPopular(moviePopularResultsItem)
        }
    }

    fun deleteFavMoviesPopular(moviePopularResultsItem: MoviePopularResult) {
        viewModelScope.launch {
            movieRepository.deleteFavMoviesPopular(moviePopularResultsItem)
        }
    }

    fun getFavMoviesUpComing() {
        viewModelScope.launch {
            _dataStateMovieUpComing.value = movieRepository.getFavMoviesUpComing()
        }
    }

    fun insertFavMoviesUpComing(movieUpComingResultsItem: MovieUpComingResult) {
        viewModelScope.launch {
            movieRepository.insertFavMoviesUpComing(movieUpComingResultsItem)
        }
    }

    fun deleteFavMoviesUpComing(movieUpComingResultsItem: MovieUpComingResult) {
        viewModelScope.launch {
            movieRepository.deleteFavMoviesUpComing(movieUpComingResultsItem)
        }
    }

}