package com.app.movie.datasource.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.movie.datasource.cache.models.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieNowPlaying(movie: MovieNowPlayingCacheEntity): Long

    @Query("SELECT * FROM MovieNowPlayingCacheEntity")
    suspend fun getMovieNowPlaying(): MovieNowPlayingCacheEntity

    @Query("SELECT * FROM MovieTopRatedCacheEntity")
    suspend fun getMovieTopRated(): MovieTopRatedCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieTopRated(movie: MovieTopRatedCacheEntity): Long

    @Query("SELECT * FROM MovieUpComingCacheEntity")
    suspend fun getMovieUpComing(): MovieUpComingCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieUpComing(movie: MovieUpComingCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviePopular(movie: MoviePopularCacheEntity): Long

    @Query("SELECT * FROM MoviePopularCacheEntity")
    suspend fun getMoviePopular(): MoviePopularCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieVideos(movieVideos: MovieVideosCacheEntity): Long

    @Query("SELECT * FROM MovieVideosCacheEntity")
    suspend fun getMovieVideos(): MovieVideosCacheEntity


}