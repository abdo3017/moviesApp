package com.app.movie.datasource.repository

import com.app.movie.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMoviesNowPlaying(): Flow<DataState<Any>>
    suspend fun getMovieVideos(id: Int): Flow<DataState<Any>>

}