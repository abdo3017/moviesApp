package com.app.movie.datasource.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.movie.datasource.cache.models.movies.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieNowPlaying(movie: MovieNowPlayingCacheEntity): Long

    @Query("SELECT * FROM MovieNowPlayingCacheEntity WHERE MovieNowPlayingCacheEntity.page Like :page")
    suspend fun getMovieNowPlaying(page: Int): MovieNowPlayingCacheEntity

    @Query("SELECT * FROM MovieTopRatedCacheEntity WHERE MovieTopRatedCacheEntity.page Like :page")
    suspend fun getMovieTopRated(page: Int): MovieTopRatedCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieTopRated(movie: MovieTopRatedCacheEntity): Long

    @Query("SELECT * FROM MovieUpComingCacheEntity WHERE MovieUpComingCacheEntity.page Like :page")
    suspend fun getMovieUpComing(page: Int): MovieUpComingCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieUpComing(movie: MovieUpComingCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviePopular(movie: MoviePopularCacheEntity): Long

    @Query("SELECT * FROM MoviePopularCacheEntity WHERE MoviePopularCacheEntity.page Like :page")
    suspend fun getMoviePopular(page: Int): MoviePopularCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieVideos(movieVideos: MovieVideosCacheEntity): Long

    @Query("SELECT * FROM MovieVideosCacheEntity")
    suspend fun getMovieVideos(): MovieVideosCacheEntity


}