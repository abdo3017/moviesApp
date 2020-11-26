package com.app.movie.di

import com.app.movie.datasource.cache.database.dao.MovieDao
import com.app.movie.datasource.cache.database.dao.TVSeriesDao
import com.app.movie.datasource.cache.mappers.*
import com.app.movie.datasource.network.api.MovieServiceImpl
import com.app.movie.datasource.network.api.TVServiceImpl
import com.app.movie.datasource.network.mappers.*
import com.app.movie.domain.repositoryimpl.MovieRepositoryImpl
import com.app.movie.domain.repositoryimpl.TVSeriesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepositoryImpl(
        movieDao: MovieDao,
        movieService: MovieServiceImpl,
        movieNowPlayingNetworkMapper: MovieNowPlayingNetworkMapper,
        movieNowPlayingCacheMapper: MovieNowPlayingCacheMapper,
        movieTopRatedNetworkMapper: MovieTopRatedNetworkMapper,
        movieTopRatedCacheMapper: MovieTopRatedCacheMapper,
        movieUpComingNetworkMapper: MovieUpComingNetworkMapper,
        movieUpComingCacheMapper: MovieUpComingCacheMapper,
        moviePopularNetworkMapper: MoviePopularNetworkMapper,
        moviePopularCacheMapper: MoviePopularCacheMapper,
        movieVideosNetworkMapper: MovieVideosNetworkMapper,
        movieVideosCacheMapper: MovieVideosCacheMapper
    ): MovieRepositoryImpl {
        return MovieRepositoryImpl(
            movieDao,
            movieService,
            movieNowPlayingNetworkMapper,
            movieNowPlayingCacheMapper,
            movieTopRatedNetworkMapper,
            movieTopRatedCacheMapper,
            movieUpComingNetworkMapper,
            movieUpComingCacheMapper,
            moviePopularNetworkMapper,
            moviePopularCacheMapper,
            movieVideosNetworkMapper,
            movieVideosCacheMapper
        )
    }

    @Singleton
    @Provides
    fun provideTVSeriesRepositoryImpl(
        tvSeriesDao: TVSeriesDao,
        tvService: TVServiceImpl,
        tvSeriesTopRatedNetworkMapper: TVSeriesTopRatedNetworkMapper,
        tvSeriesTopRatedCacheMapper: TVSeriesTopRatedCacheMapper,
        tvSeriesPopularNetworkMapper: TVSeriesPopularNetworkMapper,
        tvSeriesPopularCacheMapper: TVSeriesPopularCacheMapper,
        tvSeriesAiringTodayNetworkMapper: TVSeriesAiringTodayNetworkMapper,
        tvSeriesAiringTodayCacheMapper: TVSeriesAiringTodayCacheMapper,
        tvSeriesOnTheAirNetworkMapper: TVSeriesOnTheAirNetworkMapper,
        tvSeriesOnTheAirCacheMapper: TVSeriesOnTheAirCacheMapper

    ): TVSeriesRepositoryImpl {
        return TVSeriesRepositoryImpl(
            tvSeriesDao,
            tvService,
            tvSeriesTopRatedNetworkMapper,
            tvSeriesTopRatedCacheMapper,
            tvSeriesPopularNetworkMapper,
            tvSeriesPopularCacheMapper,
            tvSeriesAiringTodayNetworkMapper,
            tvSeriesAiringTodayCacheMapper,
            tvSeriesOnTheAirNetworkMapper,
            tvSeriesOnTheAirCacheMapper
        )
    }

}