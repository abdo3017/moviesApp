package com.app.movie.di

import com.app.movie.datasource.cache.mappers.CacheMapper
import com.app.movie.datasource.network.ApiServiceImpl
import com.app.movie.datasource.network.mappers.NetworkMapper
import com.app.movie.domain.repositoryimpl.MovieNowPlayingRepositoryImpl
import com.app.movie.datasource.cache.database.dao.MovieNowPlayingDao
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
    fun provideMovieNowPlayingRepositoryImpl(
        movieNowPlayingDao: MovieNowPlayingDao,
        apiService: ApiServiceImpl,
        networkMapper: NetworkMapper,
        cacheMapper: CacheMapper
    ): MovieNowPlayingRepositoryImpl {
        return MovieNowPlayingRepositoryImpl(
            movieNowPlayingDao, apiService, networkMapper,cacheMapper
        )
    }

}