package com.app.movie.datasource.network

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


}