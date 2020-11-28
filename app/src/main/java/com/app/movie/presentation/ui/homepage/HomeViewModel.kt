package com.app.movie.presentation.ui.homepage

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
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.domain.repositoryimpl.MovieRepositoryImpl
import com.app.movie.domain.repositoryimpl.TVSeriesRepositoryImpl
import com.app.movie.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class HomeViewModel
@ViewModelInject
constructor(
    private val movieRepository: MovieRepositoryImpl,
    private val tvSeriesRepository: TVSeriesRepositoryImpl,
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

    fun getTVSeriesTopRated(): Flow<PagingData<TVSeriesTopRatedResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { tvSeriesRepository.tvSeriesTopRatedPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}