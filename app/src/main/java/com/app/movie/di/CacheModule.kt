package com.app.movie.di

import android.content.Context
import androidx.room.Room
import com.app.movie.datasource.cache.database.AppDataBase
import com.app.movie.datasource.cache.database.dao.MovieDao
import com.app.movie.datasource.cache.database.dao.TVSeriesDao
import com.app.movie.datasource.cache.mappers.*
import com.app.movie.datasource.cache.models.*
import com.app.movie.domain.models.*
import com.app.movie.utils.Constants
import com.app.movie.utils.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {
    @Singleton
    @Provides
    fun provideMovieVideosCacheMapper(): Mapper<MovieVideosCacheEntity, MovieVideos> {
        return MovieVideosCacheMapper()
    }

    @Singleton
    @Provides
    fun provideMovieNowPlayingCacheMapper(): Mapper<MovieNowPlayingCacheEntity, MovieNowPlaying> {
        return MovieNowPlayingCacheMapper()
    }

    @Singleton
    @Provides
    fun provideMovieTopRatedCacheMapper(): Mapper<MovieTopRatedCacheEntity, MovieTopRated> {
        return MovieTopRatedCacheMapper()
    }

    @Singleton
    @Provides
    fun provideMovieUpComingCacheMapper(): Mapper<MovieUpComingCacheEntity, MovieUpComing> {
        return MovieUpComingCacheMapper()
    }

    @Singleton
    @Provides
    fun provideMoviePopularCacheMapper(): Mapper<MoviePopularCacheEntity, MoviePopular> {
        return MoviePopularCacheMapper()
    }

    @Singleton
    @Provides
    fun provideTVSeriesTopRatedCacheMapper(): Mapper<TVSeriesTopRatedCacheEntity, TVSeriesTopRated> {
        return TVSeriesTopRatedCacheMapper()
    }

    @Singleton
    @Provides
    fun provideTVSeriesPopularCacheMapper(): Mapper<TVSeriesPopularCacheEntity, TVSeriesPopular> {
        return TVSeriesPopularCacheMapper()
    }

    @Singleton
    @Provides
    fun provideTVSeriesOnTheAirCacheMapper(): Mapper<TVSeriesOnTheAirCacheEntity, TVSeriesOnTheAir> {
        return TVSeriesOnTheAirCacheMapper()
    }

    @Singleton
    @Provides
    fun provideTVSeriesAiringTodayCacheMapper(): Mapper<TVSeriesAiringTodayCacheEntity, TVSeriesAiringToday> {
        return TVSeriesAiringTodayCacheMapper()
    }

    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext context: Context): AppDataBase {
        return Room
            .databaseBuilder(
                context,
                AppDataBase::class.java,
                Constants.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(appDataBase: AppDataBase): MovieDao {
        return appDataBase.movieDao()
    }

    @Singleton
    @Provides
    fun provideTVSeriesTopRated(appDataBase: AppDataBase): TVSeriesDao {
        return appDataBase.TVSeriesDao()
    }
}