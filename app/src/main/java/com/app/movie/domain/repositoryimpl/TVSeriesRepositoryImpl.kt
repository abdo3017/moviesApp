package com.app.movie.domain.repositoryimpl

import androidx.paging.PagingSource
import com.app.movie.datasource.cache.database.dao.TVSeriesDao
import com.app.movie.datasource.cache.mappers.TVSeriesAiringTodayCacheMapper
import com.app.movie.datasource.cache.mappers.TVSeriesOnTheAirCacheMapper
import com.app.movie.datasource.cache.mappers.TVSeriesPopularCacheMapper
import com.app.movie.datasource.cache.mappers.TVSeriesTopRatedCacheMapper
import com.app.movie.datasource.cache.models.tv.TVSeriesAiringTodayCacheEntity
import com.app.movie.datasource.cache.models.tv.TVSeriesOnTheAirCacheEntity
import com.app.movie.datasource.cache.models.tv.TVSeriesPopularCacheEntity
import com.app.movie.datasource.cache.models.tv.TVSeriesTopRatedCacheEntity
import com.app.movie.datasource.network.api.TVSeries.TVServiceImpl
import com.app.movie.datasource.network.mappers.TVSeriesAiringTodayNetworkMapper
import com.app.movie.datasource.network.mappers.TVSeriesOnTheAirNetworkMapper
import com.app.movie.datasource.network.mappers.TVSeriesPopularNetworkMapper
import com.app.movie.datasource.network.mappers.TVSeriesTopRatedNetworkMapper
import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayResult
import com.app.movie.datasource.network.models.tv.TVSeriesOnTheAirResult
import com.app.movie.datasource.network.models.tv.TVSeriesPopularResult
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.domain.models.tv.TVSeriesAiringToday
import com.app.movie.domain.models.tv.TVSeriesOnTheAir
import com.app.movie.domain.models.tv.TVSeriesPopular
import com.app.movie.domain.models.tv.TVSeriesTopRated
import com.app.movie.domain.state.DataState
import com.app.movie.utils.Constants
import retrofit2.HttpException
import java.io.IOException
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
) {
    private suspend fun geTVSeriesTopRated(page: Int): DataState<TVSeriesTopRated>? {
        var tvSeriesTopRatedCacheEntity: TVSeriesTopRatedCacheEntity
        var result: DataState<TVSeriesTopRated>? = null
        kotlin.runCatching {
            tvSeriesTopRatedCacheEntity = tvSeriesDao.getTVSeriesTopRated(page)
            result = DataState.Success(
                tvSeriesTopRatedCacheMapper.mapFromEntity(
                    tvSeriesTopRatedCacheEntity
                )
            )
        }.getOrElse {
            try {
                val tvSeriesTopRatedNetworkEntity = tvService.getTVSeriesTopRated(page)
                val tvSeriesTopRated =
                    tvSeriesTopRatedNetworkMapper.mapFromEntity(tvSeriesTopRatedNetworkEntity)
                tvSeriesDao.insertTVSeriesTopRated(
                    tvSeriesTopRatedCacheMapper.mapToEntity(
                        tvSeriesTopRated
                    )
                )
                tvSeriesTopRatedCacheEntity = tvSeriesDao.getTVSeriesTopRated(page)
                result = DataState.Success(
                    tvSeriesTopRatedCacheMapper.mapFromEntity(
                        tvSeriesTopRatedCacheEntity
                    )
                )
            } catch (exception: IOException) {
                result = DataState.Error(exception) as DataState<TVSeriesTopRated>
            } catch (exception: HttpException) {
                result = DataState.Error(exception) as DataState<TVSeriesTopRated>
            }
        }
        return result
    }

    val tvSeriesTopRatedPagingSource = object : PagingSource<Int, TVSeriesTopRatedResult>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVSeriesTopRatedResult> {
            val page = params.key ?: Constants.GITHUB_STARTING_PAGE_INDEX
            return when (val tvData: DataState<TVSeriesTopRated> = geTVSeriesTopRated(page)!!) {
                is DataState.Success<TVSeriesTopRated> -> {
                    LoadResult.Page(
                        data = tvData.data.results,
                        prevKey = if (page == Constants.GITHUB_STARTING_PAGE_INDEX) null else page - 1,
                        nextKey = if (tvData.data.results.isEmpty()) null else page + 1
                    )
                }
                else -> LoadResult.Error((tvData as DataState.Error).exception)
            }
        }
    }

    private suspend fun geTVSeriesPopular(page: Int): DataState<TVSeriesPopular>? {
        var tvSeriesPopularCacheEntity: TVSeriesPopularCacheEntity
        var result: DataState<TVSeriesPopular>? = null
        kotlin.runCatching {
            tvSeriesPopularCacheEntity = tvSeriesDao.getTVSeriesPopular(page)
            result = DataState.Success(
                tvSeriesPopularCacheMapper.mapFromEntity(
                    tvSeriesPopularCacheEntity
                )
            )

        }.getOrElse {
            try {
                val tvSeriesPopularNetworkEntity = tvService.getTVSeriesPopular(page)
                val tvSeriesPopular =
                    tvSeriesPopularNetworkMapper.mapFromEntity(tvSeriesPopularNetworkEntity)
                tvSeriesDao.insertTVSeriesPopular(
                    tvSeriesPopularCacheMapper.mapToEntity(
                        tvSeriesPopular
                    )
                )
                tvSeriesPopularCacheEntity = tvSeriesDao.getTVSeriesPopular(page)
                result = DataState.Success(
                    tvSeriesPopularCacheMapper.mapFromEntity(
                        tvSeriesPopularCacheEntity
                    )
                )
            } catch (exception: IOException) {
                result = DataState.Error(exception) as DataState<TVSeriesPopular>
            } catch (exception: HttpException) {
                result = DataState.Error(exception) as DataState<TVSeriesPopular>
            }
        }
        return result
    }

    val tvSeriesPopularPagingSource = object : PagingSource<Int, TVSeriesPopularResult>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVSeriesPopularResult> {
            val page = params.key ?: Constants.GITHUB_STARTING_PAGE_INDEX
            return when (val tvData: DataState<TVSeriesPopular> = geTVSeriesPopular(page)!!) {
                is DataState.Success<TVSeriesPopular> -> {
                    LoadResult.Page(
                        data = tvData.data.results,
                        prevKey = if (page == Constants.GITHUB_STARTING_PAGE_INDEX) null else page - 1,
                        nextKey = if (tvData.data.results.isEmpty()) null else page + 1
                    )
                }
                else -> LoadResult.Error((tvData as DataState.Error).exception)
            }
        }
    }

    private suspend fun geTVSeriesOnTheAir(page: Int): DataState<TVSeriesOnTheAir>? {
        var tvSeriesOnTheAirCacheEntity: TVSeriesOnTheAirCacheEntity
        var result: DataState<TVSeriesOnTheAir>? = null
        kotlin.runCatching {
            tvSeriesOnTheAirCacheEntity = tvSeriesDao.getTVSeriesOnTheAir(page)
            result = DataState.Success(
                tvSeriesOnTheAirCacheMapper.mapFromEntity(
                    tvSeriesOnTheAirCacheEntity
                )
            )

        }.getOrElse {
            try {
                val tvSeriesOnTheAirNetworkEntity = tvService.getTVSeriesOnTheAir(page)
                val tvSeriesOnTheAir =
                    tvSeriesOnTheAirNetworkMapper.mapFromEntity(tvSeriesOnTheAirNetworkEntity)
                tvSeriesDao.insertTVSeriesOnTheAir(
                    tvSeriesOnTheAirCacheMapper.mapToEntity(
                        tvSeriesOnTheAir
                    )
                )
                tvSeriesOnTheAirCacheEntity = tvSeriesDao.getTVSeriesOnTheAir(page)
                result = DataState.Success(
                    tvSeriesOnTheAirCacheMapper.mapFromEntity(
                        tvSeriesOnTheAirCacheEntity
                    )
                )

            } catch (exception: IOException) {
                result = DataState.Error(exception) as DataState<TVSeriesOnTheAir>
            } catch (exception: HttpException) {
                result = DataState.Error(exception) as DataState<TVSeriesOnTheAir>
            }
        }
        return result
    }

    val tvSeriesOnTheAirPagingSource = object : PagingSource<Int, TVSeriesOnTheAirResult>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVSeriesOnTheAirResult> {
            val page = params.key ?: Constants.GITHUB_STARTING_PAGE_INDEX
            return when (val tvData: DataState<TVSeriesOnTheAir> = geTVSeriesOnTheAir(page)!!) {
                is DataState.Success<TVSeriesOnTheAir> -> {
                    LoadResult.Page(
                        data = tvData.data.results,
                        prevKey = if (page == Constants.GITHUB_STARTING_PAGE_INDEX) null else page - 1,
                        nextKey = if (tvData.data.results.isEmpty()) null else page + 1
                    )
                }
                else -> LoadResult.Error((tvData as DataState.Error).exception)
            }
        }
    }

    private suspend fun geTVSeriesAiringToday(page: Int): DataState<TVSeriesAiringToday>? {
        var tvSeriesAiringTodayCacheEntity: TVSeriesAiringTodayCacheEntity
        var result: DataState<TVSeriesAiringToday>? = null

        kotlin.runCatching {
            tvSeriesAiringTodayCacheEntity = tvSeriesDao.getTVSeriesAiringToday(page)
            result = DataState.Success(
                tvSeriesAiringTodayCacheMapper.mapFromEntity(
                    tvSeriesAiringTodayCacheEntity
                )
            )

        }.getOrElse {
            try {
                val tvSeriesAiringTodayNetworkEntity = tvService.getTVSeriesAiringToday(page)
                val tvSeriesAiringToday =
                    tvSeriesAiringTodayNetworkMapper.mapFromEntity(tvSeriesAiringTodayNetworkEntity)
                tvSeriesDao.insertTVSeriesAiringToday(
                    tvSeriesAiringTodayCacheMapper.mapToEntity(
                        tvSeriesAiringToday
                    )
                )
                tvSeriesAiringTodayCacheEntity = tvSeriesDao.getTVSeriesAiringToday(page)
                result = DataState.Success(
                    tvSeriesAiringTodayCacheMapper.mapFromEntity(
                        tvSeriesAiringTodayCacheEntity
                    )
                )

            } catch (exception: IOException) {
                result = DataState.Error(exception) as DataState<TVSeriesAiringToday>
            } catch (exception: HttpException) {
                result = DataState.Error(exception) as DataState<TVSeriesAiringToday>
            }
        }
        return result
    }

    val tvSeriesAiringTodayPagingSource = object : PagingSource<Int, TVSeriesAiringTodayResult>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVSeriesAiringTodayResult> {
            val page = params.key ?: Constants.GITHUB_STARTING_PAGE_INDEX
            return when (val tvData: DataState<TVSeriesAiringToday> =
                geTVSeriesAiringToday(page)!!) {
                is DataState.Success<TVSeriesAiringToday> -> {
                    LoadResult.Page(
                        data = tvData.data.results,
                        prevKey = if (page == Constants.GITHUB_STARTING_PAGE_INDEX) null else page - 1,
                        nextKey = if (tvData.data.results.isEmpty()) null else page + 1
                    )
                }
                else -> LoadResult.Error((tvData as DataState.Error).exception)
            }
        }
    }

}