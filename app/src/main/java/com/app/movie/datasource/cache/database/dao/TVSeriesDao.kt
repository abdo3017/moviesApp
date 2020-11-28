package com.app.movie.datasource.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVSeriesOnTheAir(TVSeries: TVSeriesOnTheAirCacheEntity): Long

    @Query("SELECT * FROM TVSeriesOnTheAirCacheEntity WHERE TVSeriesOnTheAirCacheEntity.page Like :page")
    suspend fun getTVSeriesOnTheAir(page: Int): TVSeriesOnTheAirCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVSeriesAiringToday(TVSeries: TVSeriesAiringTodayCacheEntity): Long

    @Query("SELECT * FROM TVSeriesAiringTodayCacheEntity WHERE TVSeriesAiringTodayCacheEntity.page Like :page")
    suspend fun getTVSeriesAiringToday(page: Int): TVSeriesAiringTodayCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVSeriesPopular(TVSeries: TVSeriesPopularCacheEntity): Long

    @Query("SELECT * FROM TVSeriesPopularCacheEntity WHERE TVSeriesPopularCacheEntity.page Like :page")
    suspend fun getTVSeriesPopular(page: Int): TVSeriesPopularCacheEntity


}