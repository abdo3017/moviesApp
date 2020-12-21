package com.app.movie.datasource.cache.database.dao

import androidx.room.*
import com.app.movie.datasource.cache.models.favouritemovies.FavMovieNowPlayingResultsItem
import com.app.movie.datasource.cache.models.favouritemovies.FavMoviePopularResult
import com.app.movie.datasource.cache.models.favouritemovies.FavMovieTopRatedResult
import com.app.movie.datasource.cache.models.favouritemovies.FavMovieUpComingResult
import com.app.movie.datasource.cache.models.movies.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieNowPlaying(movie: MovieNowPlayingCacheEntity): Long

    @Query("SELECT * FROM MovieNowPlayingCacheEntity WHERE MovieNowPlayingCacheEntity.page Like :page")
    suspend fun getMovieNowPlaying(page: Int): MovieNowPlayingCacheEntity

    @Query("SELECT * FROM FavMovieNowPlayingResultsItem")
    suspend fun getFavListMovieNowPlaying(): List<FavMovieNowPlayingResultsItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavMovieNowPlaying(movie: FavMovieNowPlayingResultsItem): Long

    @Delete
    suspend fun deleteFavMovieNowPlaying(movie: FavMovieNowPlayingResultsItem)

    @Query("SELECT * FROM MovieTopRatedCacheEntity WHERE MovieTopRatedCacheEntity.page Like :page")
    suspend fun getMovieTopRated(page: Int): MovieTopRatedCacheEntity

    @Query("SELECT * FROM FavMovieTopRatedResult ")
    suspend fun getFavListMovieTopRated(): List<FavMovieTopRatedResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavMovieTopRated(movie: FavMovieTopRatedResult): Long

    @Delete
    suspend fun deleteFavMovieTopRated(movie: FavMovieTopRatedResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieTopRated(movie: MovieTopRatedCacheEntity): Long

    @Query("SELECT * FROM MovieUpComingCacheEntity WHERE MovieUpComingCacheEntity.page Like :page")
    suspend fun getMovieUpComing(page: Int): MovieUpComingCacheEntity

    @Query("SELECT * FROM FavMovieUpComingResult")
    suspend fun getFavListMovieUpComing(): List<FavMovieUpComingResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavMovieUpComing(movie: FavMovieUpComingResult): Long

    @Delete
    suspend fun deleteFavMovieUpComing(movie: FavMovieUpComingResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieUpComing(movie: MovieUpComingCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviePopular(movie: MoviePopularCacheEntity): Long

    @Query("SELECT * FROM MoviePopularCacheEntity WHERE MoviePopularCacheEntity.page Like :page")
    suspend fun getMoviePopular(page: Int): MoviePopularCacheEntity

    @Query("SELECT * FROM FavMoviePopularResult")
    suspend fun getFavListMoviePopular(): List<FavMoviePopularResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavMoviePopular(movie: FavMoviePopularResult): Long

    @Delete
    suspend fun deleteFavMoviePopular(movie: FavMoviePopularResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieVideos(movieVideos: MovieVideosCacheEntity): Long

    @Query("SELECT * FROM MovieVideosCacheEntity")
    suspend fun getMovieVideos(): MovieVideosCacheEntity
}