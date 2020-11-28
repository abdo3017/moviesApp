package com.app.movie.datasource.network.api.TVSeries

import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayNetworkEntity
import com.app.movie.datasource.network.models.tv.TVSeriesOnTheAirNetworkEntity
import com.app.movie.datasource.network.models.tv.TVSeriesPopularNetworkEntity
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedNetworkEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TVServiceImpl
@Inject
constructor(
    private val apiService: TVService
) {

    suspend fun getTVSeriesTopRated(page: Int): TVSeriesTopRatedNetworkEntity = withContext(IO) {
        apiService.getTVSeriesTopRatedAsync(page = page).await()
    }

    suspend fun getTVSeriesOnTheAir(page: Int): TVSeriesOnTheAirNetworkEntity = withContext(IO) {
        apiService.getTVSeriesOnTheAirAsync(page = page).await()
    }

    suspend fun getTVSeriesAiringToday(page: Int): TVSeriesAiringTodayNetworkEntity =
        withContext(IO) {
            apiService.getTVSeriesAiringTodayAsync(page = page).await()
        }

    suspend fun getTVSeriesPopular(page: Int): TVSeriesPopularNetworkEntity = withContext(IO) {
        apiService.getTVSeriesPopularAsync(page = page).await()
    }


}