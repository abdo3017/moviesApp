package com.app.movie.datasource.network

import com.app.movie.datasource.network.models.MovieNowPlayingNetworkEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiServiceImpl
@Inject
constructor(
    private val apiService: ApiService) {

    suspend fun getMoviesNowPLaying(): MovieNowPlayingNetworkEntity= withContext(IO){
        apiService.getMoviesNowPLaying().await()
    }
}