package com.app.movie.datasource.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.movie.datasource.cache.models.TVSeriesAiringTodayCacheEntity
import com.app.movie.datasource.cache.models.TVSeriesOnTheAirCacheEntity
import com.app.movie.datasource.cache.models.TVSeriesPopularCacheEntity
import com.app.movie.datasource.cache.models.TVSeriesTopRatedCacheEntity

@Dao
interface TVSeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVSeriesTopRated(TVSeries: TVSeriesTopRatedCacheEntity): Long

    @Query("SELECT * FROM TVSeriesTopRatedCacheEntity")
    suspend fun getTVSeriesTopRated(): TVSeriesTopRatedCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVSeriesOnTheAir(TVSeries: TVSeriesOnTheAirCacheEntity): Long

    @Query("SELECT * FROM TVSeriesOnTheAirCacheEntity")
    suspend fun getTVSeriesOnTheAir(): TVSeriesOnTheAirCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVSeriesAiringToday(TVSeries: TVSeriesAiringTodayCacheEntity): Long

    @Query("SELECT * FROM TVSeriesAiringTodayCacheEntity")
    suspend fun getTVSeriesAiringToday(): TVSeriesAiringTodayCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVSeriesPopular(TVSeries: TVSeriesPopularCacheEntity): Long

    @Query("SELECT * FROM TVSeriesPopularCacheEntity")
    suspend fun getTVSeriesPopular(): TVSeriesPopularCacheEntity


}