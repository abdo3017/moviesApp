//package com.app.movie.datasource.cache.models
//
//import androidx.room.Embedded
//import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
//import com.app.movie.datasource.network.models.movies.MoviePopularResult
//import com.app.movie.datasource.network.models.movies.MovieTopRatedResult
//import com.app.movie.datasource.network.models.movies.MovieUpComingResult
//
//data class FavouriteMovieItems(
//    @Embedded
//    val movieNowPlayingResultsItem: MovieNowPlayingResultsItem,
//    @Embedded
//    val movieTopRatedResult: MovieTopRatedResult,
//    @Embedded
//    val moviePopularResult: MoviePopularResult,
//    @Embedded
//    val movieUpComingResult: MovieUpComingResult
//)