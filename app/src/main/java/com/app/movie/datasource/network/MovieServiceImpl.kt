package com.app.movie.datasource.network

import com.app.movie.datasource.network.models.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieServiceImpl
@Inject
constructor(
    private val apiService: MovieService
) {

    suspend fun getMoviesNowPLaying(): MovieNowPlayingNetworkEntity = withContext(IO) {
        apiService.getMoviesNowPLayingAsync().await()
    }

    suspend fun getMoviesTopRated(): MovieTopRatedNetworkEntity = withContext(IO) {
        apiService.getMoviesTopRatedAsync().await()
    }

    suspend fun getMoviesUpComing(): MovieUpComingNetworkEntity = withContext(IO) {
        apiService.getMoviesUpComingAsync().await()
    }

    suspend fun getMoviesPopular(): MoviePopularNetworkEntity = withContext(IO) {
        apiService.getMoviesPopularAsync().await()
    }

    suspend fun getMovieVideos(id: Int): MovieVideosNetworkEntity = withContext(IO) {
        apiService.getMovieVideosAsync(id = id).await()
    }
}