package com.app.movie.datasource.cache.database.dao

import androidx.room.*
import com.app.movie.datasource.cache.models.favouritetv.FavTVSeriesAiringTodayResult
import com.app.movie.datasource.cache.models.favouritetv.FavTVSeriesOnTheAirResult
import com.app.movie.datasource.cache.models.favouritetv.FavTVSeriesPopularResult
import com.app.movie.datasource.cache.models.favouritetv.FavTVSeriesTopRatedResult
import com.app.movie.datasource.cache.models.tv.TVSeriesAiringTodayCacheEntity
import com.app.movie.datasource.cache.models.tv.TVSeriesOnTheAirCacheEntity
import com.app.movie.datasource.cache.models.tv.TVSeriesPopularCacheEntity
import com.app.movie.datasource.cache.models.tv.TVSeriesTopRatedCacheEntity

@Dao
interface TVSeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVSeriesTopRated(TVSeries: TVSeriesTopRatedCacheEntity): Long

    @Query("SELECT * FROM TVSeriesTopRatedCacheEntity WHERE TVSeriesTopRatedCacheEntity.page Like :page")
    suspend fun getTVSeriesTopRated(page: Int): TVSeriesTopRatedCacheEntity

    @Query("SELECT * FROM FavTVSeriesTopRatedResult")
    suspend fun getFavListTVSeriesTopRated(): List<FavTVSeriesTopRatedResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavTVSeriesTopRated(movie: FavTVSeriesTopRatedResult): Long

    @Delete
    suspend fun deleteFavTVSeriesTopRated(movie: FavTVSeriesTopRatedResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVSeriesOnTheAir(TVSeries: TVSeriesOnTheAirCacheEntity): Long

    @Query("SELECT * FROM TVSeriesOnTheAirCacheEntity WHERE TVSeriesOnTheAirCacheEntity.page Like :page")
    suspend fun getTVSeriesOnTheAir(page: Int): TVSeriesOnTheAirCacheEntity

    @Query("SELECT * FROM FavTVSeriesOnTheAirResult")
    suspend fun getFavListTVSeriesOnTheAir(): List<FavTVSeriesOnTheAirResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavTVSeriesOnTheAir(movie: FavTVSeriesOnTheAirResult): Long

    @Delete
    suspend fun deleteFavTVSeriesOnTheAir(movie: FavTVSeriesOnTheAirResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVSeriesAiringToday(TVSeries: TVSeriesAiringTodayCacheEntity): Long

    @Query("SELECT * FROM TVSeriesAiringTodayCacheEntity WHERE TVSeriesAiringTodayCacheEntity.page Like :page")
    suspend fun getTVSeriesAiringToday(page: Int): TVSeriesAiringTodayCacheEntity

    @Query("SELECT * FROM FavTVSeriesAiringTodayResult")
    suspend fun getFavListTVSeriesAiringToday(): List<FavTVSeriesAiringTodayResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavTVSeriesAiringToday(movie: FavTVSeriesAiringTodayResult): Long

    @Delete
    suspend fun deleteFavTVSeriesAiringToday(movie: FavTVSeriesAiringTodayResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVSeriesPopular(TVSeries: TVSeriesPopularCacheEntity): Long

    @Query("SELECT * FROM TVSeriesPopularCacheEntity WHERE TVSeriesPopularCacheEntity.page Like :page")
    suspend fun getTVSeriesPopular(page: Int): TVSeriesPopularCacheEntity

    @Query("SELECT * FROM FavTVSeriesPopularResult")
    suspend fun getFavListTVSeriesPopular(): List<FavTVSeriesPopularResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavTVSeriesPopular(movie: FavTVSeriesPopularResult): Long

    @Delete
    suspend fun deleteFavTVSeriesPopular(movie: FavTVSeriesPopularResult)

}