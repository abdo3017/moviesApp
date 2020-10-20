package com.app.movie.datasource.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.movie.datasource.cache.models.MovieNowPlayingCacheEntity

@Dao
interface MovieNowPlayingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieNowPlayingCacheEntity: MovieNowPlayingCacheEntity):Long

    @Query("SELECT * FROM MovieNowPlayingCacheEntity")
    suspend fun get(): MovieNowPlayingCacheEntity
}