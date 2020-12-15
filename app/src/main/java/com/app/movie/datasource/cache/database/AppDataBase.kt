package com.app.movie.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.movie.datasource.cache.database.dao.MovieDao
import com.app.movie.datasource.cache.database.dao.TVSeriesDao
import com.app.movie.datasource.cache.models.favouritemovies.FavMovieNowPlayingResultsItem
import com.app.movie.datasource.cache.models.favouritemovies.FavMoviePopularResult
import com.app.movie.datasource.cache.models.favouritemovies.FavMovieTopRatedResult
import com.app.movie.datasource.cache.models.favouritemovies.FavMovieUpComingResult
import com.app.movie.datasource.cache.models.favouritetv.FavTVSeriesAiringTodayResult
import com.app.movie.datasource.cache.models.favouritetv.FavTVSeriesOnTheAirResult
import com.app.movie.datasource.cache.models.favouritetv.FavTVSeriesPopularResult
import com.app.movie.datasource.cache.models.favouritetv.FavTVSeriesTopRatedResult
import com.app.movie.datasource.cache.models.movies.*
import com.app.movie.datasource.cache.models.tv.TVSeriesAiringTodayCacheEntity
import com.app.movie.datasource.cache.models.tv.TVSeriesOnTheAirCacheEntity
import com.app.movie.datasource.cache.models.tv.TVSeriesPopularCacheEntity
import com.app.movie.datasource.cache.models.tv.TVSeriesTopRatedCacheEntity
import com.app.movie.utils.ModuleConverter

@Database(
    entities = [MovieNowPlayingCacheEntity::class, MovieVideosCacheEntity::class, TVSeriesTopRatedCacheEntity::class, MovieTopRatedCacheEntity::class, MovieUpComingCacheEntity::class, MoviePopularCacheEntity::class,
        TVSeriesAiringTodayCacheEntity::class, TVSeriesOnTheAirCacheEntity::class, TVSeriesPopularCacheEntity::class,
        FavMovieNowPlayingResultsItem::class, FavMovieTopRatedResult::class, FavMoviePopularResult::class, FavMovieUpComingResult::class,
        FavTVSeriesPopularResult::class, FavTVSeriesAiringTodayResult::class, FavTVSeriesOnTheAirResult::class, FavTVSeriesTopRatedResult::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ModuleConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun TVSeriesDao(): TVSeriesDao

}
