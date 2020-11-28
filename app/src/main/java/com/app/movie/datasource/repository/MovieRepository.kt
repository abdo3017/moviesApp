//package com.app.movie.datasource.repository
//
//import com.app.movie.domain.models.movies.MovieNowPlaying
//import com.app.movie.domain.state.DataState
//import kotlinx.coroutines.flow.Flow
//
//interface MovieRepository {
//    suspend fun getMoviesNowPlaying(page: Int = 1): DataState<MovieNowPlaying>?
//    suspend fun getMoviesNowPlaying(): Flow<DataState<Any>>
//
//    suspend fun getMoviesTopRated(): Flow<DataState<Any>>
//    suspend fun getMoviesUpComing(): Flow<DataState<Any>>
//    suspend fun getMoviesPopular(): Flow<DataState<Any>>
//
//    suspend fun getMovieVideos(id: Int): Flow<DataState<Any>>
//
//}