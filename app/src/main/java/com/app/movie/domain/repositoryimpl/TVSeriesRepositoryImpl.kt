package com.app.movie.domain.repositoryimpl

import com.app.movie.datasource.cache.database.dao.TVSeriesDao
import com.app.movie.datasource.cache.mappers.TVSeriesAiringTodayCacheMapper
import com.app.movie.datasource.cache.mappers.TVSeriesOnTheAirCacheMapper
import com.app.movie.datasource.cache.mappers.TVSeriesPopularCacheMapper
import com.app.movie.datasource.cache.mappers.TVSeriesTopRatedCacheMapper
import com.app.movie.datasource.cache.models.TVSeriesAiringTodayCacheEntity
import com.app.movie.datasource.cache.models.TVSeriesOnTheAirCacheEntity
import com.app.movie.datasource.cache.models.TVSeriesPopularCacheEntity
import com.app.movie.datasource.cache.models.TVSeriesTopRatedCacheEntity
import com.app.movie.datasource.network.api.TVServiceImpl
import com.app.movie.datasource.network.mappers.TVSeriesAiringTodayNetworkMapper
import com.app.movie.datasource.network.mappers.TVSeriesOnTheAirNetworkMapper
import com.app.movie.datasource.network.mappers.TVSeriesPopularNetworkMapper
import com.app.movie.datasource.network.mappers.TVSeriesTopRatedNetworkMapper
import com.app.movie.datasource.repository.TVSeriesRepository
import com.app.movie.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TVSeriesRepositoryImpl
@Inject
constructor(
    private val tvSeriesDao: TVSeriesDao,
    private val tvService: TVServiceImpl,
    private val tvSeriesTopRatedNetworkMapper: TVSeriesTopRatedNetworkMapper,
    private val tvSeriesTopRatedCacheMapper: TVSeriesTopRatedCacheMapper,
    private val tvSeriesPopularNetworkMapper: TVSeriesPopularNetworkMapper,
    private val tvSeriesPopularCacheMapper: TVSeriesPopularCacheMapper,
    private val tvSeriesAiringTodayNetworkMapper: TVSeriesAiringTodayNetworkMapper,
    private val tvSeriesAiringTodayCacheMapper: TVSeriesAiringTodayCacheMapper,
    private val tvSeriesOnTheAirNetworkMapper: TVSeriesOnTheAirNetworkMapper,
    private val tvSeriesOnTheAirCacheMapper: TVSeriesOnTheAirCacheMapper

) : TVSeriesRepository {
    override suspend fun geTVSeriesTopRated(): Flow<DataState<Any>> = flow {
        emit(DataState.Loading)
        var tvSeriesTopRatedCacheEntity: TVSeriesTopRatedCacheEntity
        kotlin.runCatching {
            tvSeriesTopRatedCacheEntity = tvSeriesDao.getTVSeriesTopRated()
            emit(
                DataState.Success(
                    tvSeriesTopRatedCacheMapper.mapFromEntity(
                        tvSeriesTopRatedCacheEntity
                    )
                )
            )
        }.getOrElse {
            try {
                val tvSeriesTopRatedNetworkEntity = tvService.getTVSeriesTopRated()
                val tvSeriesTopRated =
                    tvSeriesTopRatedNetworkMapper.mapFromEntity(tvSeriesTopRatedNetworkEntity)
                tvSeriesDao.insertTVSeriesTopRated(
                    tvSeriesTopRatedCacheMapper.mapToEntity(
                        tvSeriesTopRated
                    )
                )
                tvSeriesTopRatedCacheEntity = tvSeriesDao.getTVSeriesTopRated()
                emit(
                    DataState.Success(
                        tvSeriesTopRatedCacheMapper.mapFromEntity(
                            tvSeriesTopRatedCacheEntity
                        )
                    )
                )
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
        //delay(1000)

    }

    override suspend fun geTVSeriesPopular(): Flow<DataState<Any>> = flow {
        emit(DataState.Loading)
        var tvSeriesPopularCacheEntity: TVSeriesPopularCacheEntity
        kotlin.runCatching {
            tvSeriesPopularCacheEntity = tvSeriesDao.getTVSeriesPopular()
            emit(
                DataState.Success(
                    tvSeriesPopularCacheMapper.mapFromEntity(
                        tvSeriesPopularCacheEntity
                    )
                )
            )
        }.getOrElse {
            try {
                val tvSeriesPopularNetworkEntity = tvService.getTVSeriesPopular()
                val tvSeriesPopular =
                    tvSeriesPopularNetworkMapper.mapFromEntity(tvSeriesPopularNetworkEntity)
                tvSeriesDao.insertTVSeriesPopular(
                    tvSeriesPopularCacheMapper.mapToEntity(
                        tvSeriesPopular
                    )
                )
                tvSeriesPopularCacheEntity = tvSeriesDao.getTVSeriesPopular()
                emit(
                    DataState.Success(
                        tvSeriesPopularCacheMapper.mapFromEntity(
                            tvSeriesPopularCacheEntity
                        )
                    )
                )
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
        //delay(1000)

    }

    override suspend fun geTVSeriesOnTheAir(): Flow<DataState<Any>> = flow {
        emit(DataState.Loading)
        var tvSeriesOnTheAirCacheEntity: TVSeriesOnTheAirCacheEntity
        kotlin.runCatching {
            tvSeriesOnTheAirCacheEntity = tvSeriesDao.getTVSeriesOnTheAir()
            emit(
                DataState.Success(
                    tvSeriesOnTheAirCacheMapper.mapFromEntity(
                        tvSeriesOnTheAirCacheEntity
                    )
                )
            )
        }.getOrElse {
            try {
                val tvSeriesOnTheAirNetworkEntity = tvService.getTVSeriesOnTheAir()
                val tvSeriesOnTheAir =
                    tvSeriesOnTheAirNetworkMapper.mapFromEntity(tvSeriesOnTheAirNetworkEntity)
                tvSeriesDao.insertTVSeriesOnTheAir(
                    tvSeriesOnTheAirCacheMapper.mapToEntity(
                        tvSeriesOnTheAir
                    )
                )
                tvSeriesOnTheAirCacheEntity = tvSeriesDao.getTVSeriesOnTheAir()
                emit(
                    DataState.Success(
                        tvSeriesOnTheAirCacheMapper.mapFromEntity(
                            tvSeriesOnTheAirCacheEntity
                        )
                    )
                )
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
        //delay(1000)

    }

    override suspend fun geTVSeriesAiringToday(): Flow<DataState<Any>> = flow {
        emit(DataState.Loading)
        var tvSeriesAiringTodayCacheEntity: TVSeriesAiringTodayCacheEntity
        kotlin.runCatching {
            tvSeriesAiringTodayCacheEntity = tvSeriesDao.getTVSeriesAiringToday()
            emit(
                DataState.Success(
                    tvSeriesAiringTodayCacheMapper.mapFromEntity(
                        tvSeriesAiringTodayCacheEntity
                    )
                )
            )
        }.getOrElse {
            try {
                val tvSeriesAiringTodayNetworkEntity = tvService.getTVSeriesAiringToday()
                val tvSeriesAiringToday =
                    tvSeriesAiringTodayNetworkMapper.mapFromEntity(tvSeriesAiringTodayNetworkEntity)
                tvSeriesDao.insertTVSeriesAiringToday(
                    tvSeriesAiringTodayCacheMapper.mapToEntity(
                        tvSeriesAiringToday
                    )
                )
                tvSeriesAiringTodayCacheEntity = tvSeriesDao.getTVSeriesAiringToday()
                emit(
                    DataState.Success(
                        tvSeriesAiringTodayCacheMapper.mapFromEntity(
                            tvSeriesAiringTodayCacheEntity
                        )
                    )
                )
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
        //delay(1000)

    }
}