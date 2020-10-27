package com.app.movie.di

import com.app.movie.datasource.cache.database.dao.MovieDao
import com.app.movie.datasource.cache.mappers.MovieNowPlayingCacheMapper
import com.app.movie.datasource.cache.mappers.MovieVideosCacheMapper
import com.app.movie.datasource.network.MovieServiceImpl
import com.app.movie.datasource.network.mappers.MovieNowPlayingNetworkMapper
import com.app.movie.datasource.network.mappers.MovieVideosNetworkMapper
import com.app.movie.domain.repositoryimpl.MovieRepositoryImpl
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
        movieVideosNetworkMapper: MovieVideosNetworkMapper,
        movieVideosCacheMapper: MovieVideosCacheMapper
    ): MovieRepositoryImpl {
        return MovieRepositoryImpl(
            movieDao,
            movieService,
            movieNowPlayingNetworkMapper,
            movieNowPlayingCacheMapper,
            movieVideosNetworkMapper,
            movieVideosCacheMapper
        )
    }

}