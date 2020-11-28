package com.app.movie.presentation.ui.tv.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayResult
import com.app.movie.datasource.network.models.tv.TVSeriesOnTheAirResult
import com.app.movie.datasource.network.models.tv.TVSeriesPopularResult
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.domain.repositoryimpl.TVSeriesRepositoryImpl
import com.app.movie.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class TVSeriesViewModel
@ViewModelInject
constructor(
    private val tvSeriesRepositoryImpl: TVSeriesRepositoryImpl,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val viewModelJob = Job()

    fun getTVSeriesOnTheAir(): Flow<PagingData<TVSeriesOnTheAirResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { tvSeriesRepositoryImpl.tvSeriesOnTheAirPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getTVSeriesAiringToday(): Flow<PagingData<TVSeriesAiringTodayResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { tvSeriesRepositoryImpl.tvSeriesAiringTodayPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getTVSeriesPopular(): Flow<PagingData<TVSeriesPopularResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { tvSeriesRepositoryImpl.tvSeriesPopularPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getTVSeriesTopRated(): Flow<PagingData<TVSeriesTopRatedResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { tvSeriesRepositoryImpl.tvSeriesTopRatedPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}