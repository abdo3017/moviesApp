package com.app.movie.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.movie.datasource.cache.database.dao.MovieDao
import com.app.movie.datasource.cache.database.dao.TVSeriesDao
import com.app.movie.datasource.cache.models.movies.*
import com.app.movie.datasource.cache.models.tv.TVSeriesAiringTodayCacheEntity
import com.app.movie.datasource.cache.models.tv.TVSeriesOnTheAirCacheEntity
import com.app.movie.datasource.cache.models.tv.TVSeriesPopularCacheEntity
import com.app.movie.datasource.cache.models.tv.TVSeriesTopRatedCacheEntity
import com.app.movie.utils.ModuleConverter

@Database(
    entities = [MovieNowPlayingCacheEntity::class, MovieVideosCacheEntity::class, TVSeriesTopRatedCacheEntity::class, MovieTopRatedCacheEntity::class, MovieUpComingCacheEntity::class, MoviePopularCacheEntity::class, TVSeriesAiringTodayCacheEntity::class, TVSeriesOnTheAirCacheEntity::class, TVSeriesPopularCacheEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ModuleConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun TVSeriesDao(): TVSeriesDao

}
