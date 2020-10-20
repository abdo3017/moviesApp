package com.app.movie.datasource.repository

import com.app.movie.domain.models.MovieNowPlaying
import com.example.movieapp.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface MovieNowPlayingRepository {
    suspend fun getMoviesNowPlaying(): Flow<DataState<MovieNowPlaying>>
}