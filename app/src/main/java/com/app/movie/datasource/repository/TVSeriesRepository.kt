//package com.app.movie.datasource.repository
//
//import com.app.movie.domain.state.DataState
//import kotlinx.coroutines.flow.Flow
//
//interface TVSeriesRepository {
//    suspend fun geTVSeriesTopRated(): Flow<DataState<Any>>
//    suspend fun geTVSeriesPopular(): Flow<DataState<Any>>
//    suspend fun geTVSeriesOnTheAir(): Flow<DataState<Any>>
//    suspend fun geTVSeriesAiringToday(): Flow<DataState<Any>>
//}