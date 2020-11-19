package com.app.movie.datasource.network

import com.app.movie.datasource.network.models.TVSeriesAiringTodayNetworkEntity
import com.app.movie.datasource.network.models.TVSeriesOnTheAirNetworkEntity
import com.app.movie.datasource.network.models.TVSeriesPopularNetworkEntity
import com.app.movie.datasource.network.models.TVSeriesTopRatedNetworkEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TVServiceImpl
@Inject
constructor(
    private val apiService: TVService
) {

    suspend fun getTVSeriesTopRated(): TVSeriesTopRatedNetworkEntity = withContext(IO) {
        apiService.getTVSeriesTopRatedAsync().await()
    }

    suspend fun getTVSeriesOnTheAir(): TVSeriesOnTheAirNetworkEntity = withContext(IO) {
        apiService.getTVSeriesOnTheAirAsync().await()
    }

    suspend fun getTVSeriesAiringToday(): TVSeriesAiringTodayNetworkEntity = withContext(IO) {
        apiService.getTVSeriesAiringTodayAsync().await()
    }

    suspend fun getTVSeriesPopular(): TVSeriesPopularNetworkEntity = withContext(IO) {
        apiService.getTVSeriesPopularAsync().await()
    }


}