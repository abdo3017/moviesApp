package com.app.movie.datasource.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.movie.datasource.cache.models.MovieNowPlayingCacheEntity
import com.app.movie.datasource.cache.models.MovieVideosCacheEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieNowPlaying(movieNowPlayingCacheEntity: MovieNowPlayingCacheEntity): Long

    @Query("SELECT * FROM MovieNowPlayingCacheEntity")
    suspend fun getMovieNowPlaying(): MovieNowPlayingCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieVideos(movieVideosCacheEntity: MovieVideosCacheEntity): Long

    @Query("SELECT * FROM MovieVideosCacheEntity")
    suspend fun getMovieVideos(): MovieVideosCacheEntity


}