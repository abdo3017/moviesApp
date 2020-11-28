package com.app.movie.presentation.ui.movies.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
import com.app.movie.datasource.network.models.movies.MoviePopularResult
import com.app.movie.datasource.network.models.movies.MovieTopRatedResult
import com.app.movie.datasource.network.models.movies.MovieUpComingResult
import com.app.movie.domain.repositoryimpl.MovieRepositoryImpl
import com.app.movie.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class MoviesViewModel
@ViewModelInject
constructor(
    private val movieRepository: MovieRepositoryImpl,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val viewModelJob = Job()

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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}