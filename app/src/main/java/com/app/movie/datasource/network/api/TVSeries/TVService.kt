package com.app.movie.datasource.network.api.TVSeries

import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayNetworkEntity
import com.app.movie.datasource.network.models.tv.TVSeriesOnTheAirNetworkEntity
import com.app.movie.datasource.network.models.tv.TVSeriesPopularNetworkEntity
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedNetworkEntity
import com.app.movie.utils.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface TVService {
    @GET(Constants.TOP_RATED_TV)
    fun getTVSeriesTopRatedAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int
    ): Deferred<TVSeriesTopRatedNetworkEntity>


    @GET(Constants.ON_THE_AIR_TV)
    fun getTVSeriesOnTheAirAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int
    ): Deferred<TVSeriesOnTheAirNetworkEntity>


    @GET(Constants.AIRING_TODAY_TV)
    fun getTVSeriesAiringTodayAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int
    ): Deferred<TVSeriesAiringTodayNetworkEntity>

    @GET(Constants.POPULAR_TV)
    fun getTVSeriesPopularAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int
    ): Deferred<TVSeriesPopularNetworkEntity>


}