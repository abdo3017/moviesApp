package com.app.movie.domain.repositoryimpl

import androidx.paging.PagingSource
import com.app.movie.datasource.cache.database.dao.MovieDao
import com.app.movie.datasource.cache.mappers.*
import com.app.movie.datasource.cache.models.*
import com.app.movie.datasource.network.api.MovieServiceImpl
import com.app.movie.datasource.network.mappers.*
import com.app.movie.datasource.network.models.MovieNowPlayingResultsItem
import com.app.movie.datasource.repository.MovieRepository
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.domain.state.DataState
import com.app.movie.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl
@Inject
constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieServiceImpl,
    private val movieNowPlayingNetworkMapper: MovieNowPlayingNetworkMapper,
    private val movieNowPlayingCacheMapper: MovieNowPlayingCacheMapper,
    private val movieTopRatedNetworkMapper: MovieTopRatedNetworkMapper,
    private val movieTopRatedCacheMapper: MovieTopRatedCacheMapper,
    private val movieUpComingNetworkMapper: MovieUpComingNetworkMapper,
    private val movieUpComingCacheMapper: MovieUpComingCacheMapper,
    private val moviePopularNetworkMapper: MoviePopularNetworkMapper,
    private val moviePopularCacheMapper: MoviePopularCacheMapper,
    private val movieVideosNetworkMapper: MovieVideosNetworkMapper,
    private val movieVideosCacheMapper: MovieVideosCacheMapper
) : MovieRepository {

    override suspend fun getMoviesNowPlaying(page: Int): DataState<MovieNowPlaying>? {
        var movieNowPlayingCacheEntity: MovieNowPlayingCacheEntity
        var result: DataState<MovieNowPlaying>? = null
        kotlin.runCatching {
            movieNowPlayingCacheEntity = movieDao.getMovieNowPlaying(page)
            result = DataState.Success(
                movieNowPlayingCacheMapper.mapFromEntity(
                    movieNowPlayingCacheEntity
                )
            )

        }.getOrElse {
            try {
                val movieNowPlayingNetworkEntity = movieService.getMoviesNowPLaying(page)
                val movieNowPlaying =
                    movieNowPlayingNetworkMapper.mapFromEntity(movieNowPlayingNetworkEntity)
                movieDao.insertMovieNowPlaying(
                    movieNowPlayingCacheMapper.mapToEntity(
                        movieNowPlaying
                    )
                )
                movieNowPlayingCacheEntity = movieDao.getMovieNowPlaying(page)
                result = DataState.Success(
                    movieNowPlayingCacheMapper.mapFromEntity(
                        movieNowPlayingCacheEntity
                    )
                )

            } catch (exception: IOException) {
                result = DataState.Error(exception) as DataState<MovieNowPlaying>
            } catch (exception: HttpException) {
                result = DataState.Error(exception) as DataState<MovieNowPlaying>
            }
            //delay(1000)
        }

        return result
    }

    override suspend fun getMoviesNowPlaying(): Flow<DataState<Any>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMoviesTopRated(): Flow<DataState<Any>> = flow {
        emit(DataState.Loading)
        var movieTopRatedCacheEntity: MovieTopRatedCacheEntity
        kotlin.runCatching {
            movieTopRatedCacheEntity = movieDao.getMovieTopRated()
            emit(
                DataState.Success(
                    movieTopRatedCacheMapper.mapFromEntity(
                        movieTopRatedCacheEntity
                    )
                )
            )
        }.getOrElse {
            try {
                val movieTopRatedNetworkEntity = movieService.getMoviesTopRated()
                val movieTopRated =
                    movieTopRatedNetworkMapper.mapFromEntity(movieTopRatedNetworkEntity)
                movieDao.insertMovieTopRated(movieTopRatedCacheMapper.mapToEntity(movieTopRated))
                movieTopRatedCacheEntity = movieDao.getMovieTopRated()
                emit(
                    DataState.Success(
                        movieTopRatedCacheMapper.mapFromEntity(
                            movieTopRatedCacheEntity
                        )
                    )
                )
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
        //delay(1000)

    }

    override suspend fun getMoviesUpComing(): Flow<DataState<Any>> = flow {
        emit(DataState.Loading)
        var movieUpComingCacheEntity: MovieUpComingCacheEntity
        kotlin.runCatching {
            movieUpComingCacheEntity = movieDao.getMovieUpComing()
            emit(
                DataState.Success(
                    movieUpComingCacheMapper.mapFromEntity(
                        movieUpComingCacheEntity
                    )
                )
            )
        }.getOrElse {
            try {
                val movieUpComingNetworkEntity = movieService.getMoviesUpComing()
                val movieUpComing =
                    movieUpComingNetworkMapper.mapFromEntity(movieUpComingNetworkEntity)
                movieDao.insertMovieUpComing(movieUpComingCacheMapper.mapToEntity(movieUpComing))
                movieUpComingCacheEntity = movieDao.getMovieUpComing()
                emit(
                    DataState.Success(
                        movieUpComingCacheMapper.mapFromEntity(
                            movieUpComingCacheEntity
                        )
                    )
                )
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
        //delay(1000)

    }

    override suspend fun getMoviesPopular(): Flow<DataState<Any>> = flow {
        emit(DataState.Loading)
        var moviePopularCacheEntity: MoviePopularCacheEntity
        kotlin.runCatching {
            moviePopularCacheEntity = movieDao.getMoviePopular()
            emit(
                DataState.Success(
                    moviePopularCacheMapper.mapFromEntity(
                        moviePopularCacheEntity
                    )
                )
            )
        }.getOrElse {
            try {
                val moviePopularNetworkEntity = movieService.getMoviesPopular()
                val moviePopular =
                    moviePopularNetworkMapper.mapFromEntity(moviePopularNetworkEntity)
                movieDao.insertMoviePopular(
                    moviePopularCacheMapper.mapToEntity(
                        moviePopular
                    )
                )
                moviePopularCacheEntity = movieDao.getMoviePopular()
                emit(
                    DataState.Success(
                        moviePopularCacheMapper.mapFromEntity(
                            moviePopularCacheEntity
                        )
                    )
                )
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }

            //delay(1000)

        }
    }

    override suspend fun getMovieVideos(id: Int): Flow<DataState<Any>> = flow {
        emit(DataState.Loading)
        var movieVideosCacheEntity: MovieVideosCacheEntity
        kotlin.runCatching {
            movieVideosCacheEntity = movieDao.getMovieVideos()

            emit(
                DataState.Success(
                    movieVideosCacheMapper.mapFromEntity(
                        movieVideosCacheEntity
                    )
                )
            )
        }.getOrElse {
            try {
                val movieVideosNetworkEntity = movieService.getMovieVideos(id)
                val movieVideos =
                    movieVideosNetworkMapper.mapFromEntity(movieVideosNetworkEntity)
                movieDao.insertMovieVideos(movieVideosCacheMapper.mapToEntity(movieVideos))
                movieVideosCacheEntity = movieDao.getMovieVideos()

                emit(
                    DataState.Success(
                        movieVideosCacheMapper.mapFromEntity(
                            movieVideosCacheEntity
                        )
                    )
                )
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
        //delay(1000)

    }

    val moviePlayingNowPagingSource = object : PagingSource<Int, MovieNowPlayingResultsItem>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieNowPlayingResultsItem> {
            val page = params.key ?: Constants.GITHUB_STARTING_PAGE_INDEX
            return when (val movieData: DataState<MovieNowPlaying> = getMoviesNowPlaying(page)!!) {
                is DataState.Success<MovieNowPlaying> -> {
                    LoadResult.Page(
                        data = movieData.data.results,
                        prevKey = if (page == Constants.GITHUB_STARTING_PAGE_INDEX) null else page - 1,
                        nextKey = if (movieData.data.results.isEmpty()) null else page + 1
                    )
                }
                else -> LoadResult.Error((movieData as DataState.Error).exception)
            }
        }

    }

}