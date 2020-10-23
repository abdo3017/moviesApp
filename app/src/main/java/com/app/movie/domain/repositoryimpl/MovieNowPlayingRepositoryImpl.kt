package com.app.movie.domain.repositoryimpl

import com.app.movie.datasource.cache.mappers.CacheMapper
import com.app.movie.datasource.network.ApiServiceImpl
import com.app.movie.datasource.network.mappers.NetworkMapper
import com.app.movie.datasource.repository.MovieNowPlayingRepository
import com.example.movieapp.domain.state.DataState
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.datasource.cache.database.dao.MovieNowPlayingDao
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MovieNowPlayingRepositoryImpl
@Inject
constructor(
    private val movieNowPlayingDao: MovieNowPlayingDao,
    private val apiService: ApiServiceImpl,
    private val networkMapper: NetworkMapper,
    private val cacheMapper: CacheMapper
) : MovieNowPlayingRepository {

    override suspend fun getMoviesNowPlaying(): Flow<DataState<MovieNowPlaying>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val movieNowPlayingNetworkEntity = apiService.getMoviesNowPLaying()
            val movieNowPlaying = networkMapper.mapFromEntity(movieNowPlayingNetworkEntity)
            movieNowPlayingDao.insert(cacheMapper.mapToEntity(movieNowPlaying))
            val movieNowPlayingCacheEntity = movieNowPlayingDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntity(movieNowPlayingCacheEntity)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}