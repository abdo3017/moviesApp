package com.app.movie.datasource.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.movie.datasource.cache.models.TVSeriesTopRatedCacheEntity

@Dao
interface TVSeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVSeriesTopRated(TVSeriesTopRatedCacheEntity: TVSeriesTopRatedCacheEntity): Long

    @Query("SELECT * FROM TVSeriesTopRatedCacheEntity")
    suspend fun getTVSeriesTopRated(): TVSeriesTopRatedCacheEntity


}