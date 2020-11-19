package com.app.movie.datasource.network

import com.app.movie.datasource.network.models.*
import com.app.movie.utils.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {
    @GET(Constants.NOW_PLAYING_MOVIE)
    fun getMoviesNowPLayingAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int = 1
    ): Deferred<MovieNowPlayingNetworkEntity>

    @GET(Constants.TOP_RATED_MOVIE)
    fun getMoviesTopRatedAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int = 1
    ): Deferred<MovieTopRatedNetworkEntity>

    @GET(Constants.UP_COMING_MOVIE)
    fun getMoviesUpComingAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int = 1
    ): Deferred<MovieUpComingNetworkEntity>

    @GET(Constants.UP_COMING_MOVIE)
    fun getMoviesPopularAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int = 1
    ): Deferred<MoviePopularNetworkEntity>

    @GET(Constants.VIDEO)
    fun getMovieVideosAsync(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE
    ): Deferred<MovieVideosNetworkEntity>
}