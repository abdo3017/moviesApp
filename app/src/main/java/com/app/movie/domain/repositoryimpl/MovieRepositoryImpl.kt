package com.app.movie.domain.repositoryimpl

import android.util.Log
import com.app.movie.datasource.cache.database.dao.MovieDao
import com.app.movie.datasource.cache.mappers.MovieNowPlayingCacheMapper
import com.app.movie.datasource.cache.mappers.MovieVideosCacheMapper
import com.app.movie.datasource.network.MovieServiceImpl
import com.app.movie.datasource.network.mappers.MovieNowPlayingNetworkMapper
import com.app.movie.datasource.network.mappers.MovieVideosNetworkMapper
import com.app.movie.datasource.repository.MovieRepository
import com.app.movie.domain.state.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl
@Inject
constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieServiceImpl,
    private val movieNowPlayingNetworkMapper: MovieNowPlayingNetworkMapper,
    private val movieNowPlayingCacheMapper: MovieNowPlayingCacheMapper,
    private val movieVideosNetworkMapper: MovieVideosNetworkMapper,
    private val movieVideosCacheMapper: MovieVideosCacheMapper
) : MovieRepository {

    override suspend fun getMoviesNowPlaying(): Flow<DataState<Any>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val movieNowPlayingNetworkEntity = movieService.getMoviesNowPLaying()
            val movieNowPlaying =
                movieNowPlayingNetworkMapper.mapFromEntity(movieNowPlayingNetworkEntity)
            movieDao.insertMovieNowPlaying(movieNowPlayingCacheMapper.mapToEntity(movieNowPlaying))
            val movieNowPlayingCacheEntity = movieDao.getMovieNowPlaying()
            emit(
                DataState.Success(
                    movieNowPlayingCacheMapper.mapFromEntity(
                        movieNowPlayingCacheEntity
                    )
                )
            )
        } catch (e: Exception) {
            emit(DataState.Error<Any>(e))
        }
    }

    override suspend fun getMovieVideos(id: Int): Flow<DataState<Any>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val movieVideosNetworkEntity = movieService.getMovieVideos(id)
            val movieVideos =
                movieVideosNetworkMapper.mapFromEntity(movieVideosNetworkEntity)
            val x = movieDao.insertMovieVideos(movieVideosCacheMapper.mapToEntity(movieVideos))
            val movieVideosCacheEntity = movieDao.getMovieVideos()
            Log.d("movieee", movieVideosCacheEntity.results!![0]!!.key.toString())

            emit(
                DataState.Success(
                    movieVideosCacheMapper.mapFromEntity(
                        movieVideosCacheEntity
                    )
                )
            )
        } catch (e: Exception) {
            emit(DataState.Error<Any>(e))
        }
    }
}