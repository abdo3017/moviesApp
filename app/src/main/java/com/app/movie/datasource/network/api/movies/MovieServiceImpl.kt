package com.app.movie.datasource.network.api.movies

import com.app.movie.datasource.network.models.movies.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieServiceImpl
@Inject
constructor(
    private val apiService: MovieService
) {

    suspend fun getMoviesNowPLaying(page: Int): MovieNowPlayingNetworkEntity = withContext(IO) {
        apiService.getMoviesNowPLayingAsync(page = page).await()
    }

    suspend fun getMoviesTopRated(page: Int): MovieTopRatedNetworkEntity = withContext(IO) {
        apiService.getMoviesTopRatedAsync(page = page).await()
    }

    suspend fun getMoviesUpComing(page: Int): MovieUpComingNetworkEntity = withContext(IO) {
        apiService.getMoviesUpComingAsync(page = page).await()
    }

    suspend fun getMoviesPopular(page: Int): MoviePopularNetworkEntity = withContext(IO) {
        apiService.getMoviesPopularAsync(page = page).await()
    }

    suspend fun getMovieVideos(id: Int): MovieVideosNetworkEntity = withContext(IO) {
        apiService.getMovieVideosAsync(id = id).await()
    }
}