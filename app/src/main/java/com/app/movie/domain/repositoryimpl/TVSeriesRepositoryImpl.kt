package com.app.movie.domain.repositoryimpl

import com.app.movie.datasource.cache.database.dao.TVSeriesDao
import com.app.movie.datasource.cache.mappers.TVSeriesTopRatedCacheMapper
import com.app.movie.datasource.network.TVServiceImpl
import com.app.movie.datasource.network.mappers.TVSeriesTopRatedNetworkMapper
import com.app.movie.datasource.repository.TVSeriesRepository
import com.app.movie.domain.state.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TVSeriesRepositoryImpl
@Inject
constructor(
    private val tvSeriesDao: TVSeriesDao,
    private val tvService: TVServiceImpl,
    private val tvSeriesTopRatedNetworkMapper: TVSeriesTopRatedNetworkMapper,
    private val tvSeriesTopRatedCacheMapper: TVSeriesTopRatedCacheMapper
) : TVSeriesRepository {
    override suspend fun geTVSeriesTopRated(): Flow<DataState<Any>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val tvSeriesTopRatedNetworkEntity = tvService.getTVSeriesTopRated()
            val tvSeriesTopRated =
                tvSeriesTopRatedNetworkMapper.mapFromEntity(tvSeriesTopRatedNetworkEntity)
            tvSeriesDao.insertTVSeriesTopRated(
                tvSeriesTopRatedCacheMapper.mapToEntity(
                    tvSeriesTopRated
                )
            )
            val tvSeriesTopRatedCacheEntity = tvSeriesDao.getTVSeriesTopRated()
            emit(
                DataState.Success(
                    tvSeriesTopRatedCacheMapper.mapFromEntity(
                        tvSeriesTopRatedCacheEntity
                    )
                )
            )
        } catch (e: Exception) {
            emit(DataState.Error<Any>(e))
        }
    }
}