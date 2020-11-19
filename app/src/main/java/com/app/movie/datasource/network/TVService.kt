package com.app.movie.datasource.network

import com.app.movie.datasource.network.models.TVSeriesAiringTodayNetworkEntity
import com.app.movie.datasource.network.models.TVSeriesOnTheAirNetworkEntity
import com.app.movie.datasource.network.models.TVSeriesPopularNetworkEntity
import com.app.movie.datasource.network.models.TVSeriesTopRatedNetworkEntity
import com.app.movie.utils.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface TVService {
    @GET(Constants.TOP_RATED_TV)
    fun getTVSeriesTopRatedAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int = 1
    ): Deferred<TVSeriesTopRatedNetworkEntity>


    @GET(Constants.ON_THE_AIR_TV)
    fun getTVSeriesOnTheAirAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int = 1
    ): Deferred<TVSeriesOnTheAirNetworkEntity>


    @GET(Constants.AIRING_TODAY_TV)
    fun getTVSeriesAiringTodayAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int = 1
    ): Deferred<TVSeriesAiringTodayNetworkEntity>

    @GET(Constants.UP_COMING_MOVIE)
    fun getTVSeriesPopularAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int = 1
    ): Deferred<TVSeriesPopularNetworkEntity>


}