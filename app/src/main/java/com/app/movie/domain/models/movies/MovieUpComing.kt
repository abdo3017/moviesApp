package com.app.movie.domain.models.movies

import com.app.movie.datasource.network.models.movies.MovieUpComingDates
import com.app.movie.datasource.network.models.movies.MovieUpComingResult


data class MovieUpComing(
    val dates: MovieUpComingDates = MovieUpComingDates(),
    val page: Int = 0,
    val results: List<MovieUpComingResult> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)

