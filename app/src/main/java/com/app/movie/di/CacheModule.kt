package com.app.movie.di

import android.content.Context
import androidx.room.Room
import com.app.movie.datasource.cache.database.AppDataBase
import com.app.movie.datasource.cache.database.dao.MovieDao
import com.app.movie.datasource.cache.mappers.MovieNowPlayingCacheMapper
import com.app.movie.datasource.cache.models.MovieNowPlayingCacheEntity
import com.app.movie.domain.models.MovieNowPlaying
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
    fun provideMovieNowPlayingCacheMapper(): Mapper<MovieNowPlayingCacheEntity, MovieNowPlaying> {
        return MovieNowPlayingCacheMapper()
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

}