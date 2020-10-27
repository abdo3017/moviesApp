package com.app.movie.datasource.repository

import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.domain.models.MovieVideos
import com.app.movie.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMoviesNowPlaying(): Flow<DataState<MovieNowPlaying>>
    suspend fun getMovieVideos(id: Int): Flow<DataState<MovieVideos>>

}