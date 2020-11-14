package com.app.movie.datasource.repository

import com.app.movie.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface TVSeriesRepository {
    suspend fun geTVSeriesTopRated(): Flow<DataState<Any>>

}