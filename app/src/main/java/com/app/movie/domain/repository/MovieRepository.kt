package com.app.movie.domain.repository

import androidx.paging.PagingSource
import com.app.movie.datasource.cache.database.dao.MovieDao
import com.app.movie.datasource.cache.mappers.*
import com.app.movie.datasource.cache.models.favouritemovies.asDomainModelList
import com.app.movie.datasource.cache.models.movies.*
import com.app.movie.datasource.network.api.movies.MovieServiceImpl
import com.app.movie.datasource.network.mappers.*
import com.app.movie.datasource.network.models.movies.*
import com.app.movie.domain.models.movies.MovieNowPlaying
import com.app.movie.domain.models.movies.MoviePopular
import com.app.movie.domain.models.movies.MovieTopRated
import com.app.movie.domain.models.movies.MovieUpComing
import com.app.movie.domain.state.DataState
import com.app.movie.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepository
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
) {
    private suspend fun getMoviesNowPlaying(page: Int): DataState<MovieNowPlaying>? {
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
        }

        return result
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

    private suspend fun getMoviesTopRated(page: Int): DataState<MovieTopRated>? {
        var movieTopRatedCacheEntity: MovieTopRatedCacheEntity
        var result: DataState<MovieTopRated>? = null
        kotlin.runCatching {
            movieTopRatedCacheEntity = movieDao.getMovieTopRated(page)
            result = DataState.Success(
                movieTopRatedCacheMapper.mapFromEntity(
                    movieTopRatedCacheEntity
                )
            )
        }.getOrElse {
            try {
                val movieTopRatedNetworkEntity = movieService.getMoviesTopRated(page)
                val movieTopRated =
                    movieTopRatedNetworkMapper.mapFromEntity(movieTopRatedNetworkEntity)
                movieDao.insertMovieTopRated(movieTopRatedCacheMapper.mapToEntity(movieTopRated))
                movieTopRatedCacheEntity = movieDao.getMovieTopRated(page)
                result = DataState.Success(
                    movieTopRatedCacheMapper.mapFromEntity(
                        movieTopRatedCacheEntity
                    )
                )

            } catch (exception: IOException) {
                result = DataState.Error(exception) as DataState<MovieTopRated>
            } catch (exception: HttpException) {
                result = DataState.Error(exception) as DataState<MovieTopRated>
            }
        }
        return result
    }

    val movieTopRatedPagingSource = object : PagingSource<Int, MovieTopRatedResult>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieTopRatedResult> {
            val page = params.key ?: Constants.GITHUB_STARTING_PAGE_INDEX
            return when (val movieData: DataState<MovieTopRated> = getMoviesTopRated(page)!!) {
                is DataState.Success<MovieTopRated> -> {
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

    private suspend fun getMoviesUpComing(page: Int): DataState<MovieUpComing>? {
        var movieUpComingCacheEntity: MovieUpComingCacheEntity
        var result: DataState<MovieUpComing>? = null
        kotlin.runCatching {
            movieUpComingCacheEntity = movieDao.getMovieUpComing(page)
            result = DataState.Success(
                movieUpComingCacheMapper.mapFromEntity(
                    movieUpComingCacheEntity
                )
            )

        }.getOrElse {
            try {
                val movieUpComingNetworkEntity = movieService.getMoviesUpComing(page)
                val movieUpComing =
                    movieUpComingNetworkMapper.mapFromEntity(movieUpComingNetworkEntity)
                movieDao.insertMovieUpComing(movieUpComingCacheMapper.mapToEntity(movieUpComing))
                movieUpComingCacheEntity = movieDao.getMovieUpComing(page)
                result = DataState.Success(
                    movieUpComingCacheMapper.mapFromEntity(
                        movieUpComingCacheEntity
                    )
                )

            } catch (exception: IOException) {
                result = DataState.Error(exception) as DataState<MovieUpComing>
            } catch (exception: HttpException) {
                result = DataState.Error(exception) as DataState<MovieUpComing>
            }
        }
        return result
    }

    val movieUpComingPagingSource = object : PagingSource<Int, MovieUpComingResult>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieUpComingResult> {
            val page = params.key ?: Constants.GITHUB_STARTING_PAGE_INDEX
            return when (val movieData: DataState<MovieUpComing> = getMoviesUpComing(page)!!) {
                is DataState.Success<MovieUpComing> -> {
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

    private suspend fun getMoviesPopular(page: Int): DataState<MoviePopular>? {
        var moviePopularCacheEntity: MoviePopularCacheEntity
        var result: DataState<MoviePopular>? = null
        kotlin.runCatching {
            moviePopularCacheEntity = movieDao.getMoviePopular(page)
            result = DataState.Success(
                moviePopularCacheMapper.mapFromEntity(
                    moviePopularCacheEntity
                )
            )
        }.getOrElse {
            try {
                val moviePopularNetworkEntity = movieService.getMoviesPopular(page)
                val moviePopular =
                    moviePopularNetworkMapper.mapFromEntity(moviePopularNetworkEntity)
                movieDao.insertMoviePopular(
                    moviePopularCacheMapper.mapToEntity(
                        moviePopular
                    )
                )
                moviePopularCacheEntity = movieDao.getMoviePopular(page)
                result = DataState.Success(
                    moviePopularCacheMapper.mapFromEntity(
                        moviePopularCacheEntity
                    )
                )
            } catch (exception: IOException) {
                result = DataState.Error(exception) as DataState<MoviePopular>
            } catch (exception: HttpException) {
                result = DataState.Error(exception) as DataState<MoviePopular>
            }
        }
        return result
    }

    val moviePopularPagingSource = object : PagingSource<Int, MoviePopularResult>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviePopularResult> {
            val page = params.key ?: Constants.GITHUB_STARTING_PAGE_INDEX
            return when (val movieData: DataState<MoviePopular> = getMoviesPopular(page)!!) {
                is DataState.Success<MoviePopular> -> {
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

    suspend fun getMovieVideos(id: Int): Flow<DataState<Any>> = flow {
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

    }

    suspend fun getFavMoviesNowPlaying(): List<MovieNowPlayingResultsItem> {
        return try {
            val favMovieNowPlayingResultsItemList = movieDao.getFavListMovieNowPlaying()
            favMovieNowPlayingResultsItemList.asDomainModelList()
        } catch (e: java.lang.Exception) {
            emptyList()
        }
    }

    suspend fun insertFavMoviesNowPlaying(movieNowPlayingResultsItem: MovieNowPlayingResultsItem) {
        try {
            movieDao.insertFavMovieNowPlaying(movieNowPlayingResultsItem.asDomainModel())
        } catch (e: java.lang.Exception) {
        }
    }

    suspend fun deleteFavMoviesNowPlaying(movieNowPlayingResultsItem: MovieNowPlayingResultsItem) {
        try {
            movieDao.deleteFavMovieNowPlaying(movieNowPlayingResultsItem.asDomainModel())
        } catch (e: java.lang.Exception) {
        }
    }

    suspend fun getFavMoviesTopRated(): List<MovieTopRatedResult> {
        return try {
            val favMovieTopRatedResultsItemList = movieDao.getFavListMovieTopRated()
            favMovieTopRatedResultsItemList.asDomainModelList()
        } catch (e: java.lang.Exception) {
            emptyList()
        }
    }

    suspend fun insertFavMoviesTopRated(movieTopRatedResult: MovieTopRatedResult) {
        try {
            movieDao.insertFavMovieTopRated(movieTopRatedResult.asDomainModel())
        } catch (e: java.lang.Exception) {
        }
    }

    suspend fun deleteFavMoviesTopRated(movieTopRatedResult: MovieTopRatedResult) {
        try {
            movieDao.deleteFavMovieTopRated(movieTopRatedResult.asDomainModel())
        } catch (e: java.lang.Exception) {
        }
    }

    suspend fun getFavMoviesUpComing(): List<MovieUpComingResult> {
        return try {
            val favMovieUpComingResultsItemList = movieDao.getFavListMovieUpComing()
            favMovieUpComingResultsItemList.asDomainModelList()
        } catch (e: java.lang.Exception) {
            emptyList()
        }
    }

    suspend fun insertFavMoviesUpComing(movieUpComingResult: MovieUpComingResult) {
        try {
            movieDao.insertFavMovieUpComing(movieUpComingResult.asDomainModel())
        } catch (e: java.lang.Exception) {
        }
    }

    suspend fun deleteFavMoviesUpComing(movieUpComingResult: MovieUpComingResult) {
        try {
            movieDao.deleteFavMovieUpComing(movieUpComingResult.asDomainModel())
        } catch (e: java.lang.Exception) {
        }
    }

    suspend fun getFavMoviesPopular(): List<MoviePopularResult> {
        return try {
            val favMoviePopularResultsItemList = movieDao.getFavListMoviePopular()
            favMoviePopularResultsItemList.asDomainModelList()
        } catch (e: java.lang.Exception) {
            emptyList()
        }
    }

    suspend fun insertFavMoviesPopular(moviePopularResultsItem: MoviePopularResult) {
        try {
            movieDao.insertFavMoviePopular(moviePopularResultsItem.asDomainModel())
        } catch (e: java.lang.Exception) {
        }
    }

    suspend fun deleteFavMoviesPopular(moviePopularResultsItem: MoviePopularResult) {
        try {
            movieDao.deleteFavMoviePopular(moviePopularResultsItem.asDomainModel())
        } catch (e: java.lang.Exception) {
        }
    }
}