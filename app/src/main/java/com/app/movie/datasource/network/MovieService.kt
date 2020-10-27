package com.app.movie.datasource.network

import com.app.movie.datasource.network.models.MovieNowPlayingNetworkEntity
import com.app.movie.datasource.network.models.MovieVideosNetworkEntity
import com.app.movie.utils.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {
    @GET(Constants.NOW_PLAYING)
    fun getMoviesNowPLayingAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int = 1
    ): Deferred<MovieNowPlayingNetworkEntity>

    @GET(Constants.VIDEO)
    fun getMovieVideosAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Path("movie_id") id: Int
    ): Deferred<MovieVideosNetworkEntity>
}