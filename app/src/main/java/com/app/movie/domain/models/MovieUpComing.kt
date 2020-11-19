package com.app.movie.domain.models

import com.app.movie.datasource.network.models.MovieUpComingDates
import com.app.movie.datasource.network.models.MovieUpComingResult


data class MovieUpComing(
    val dates: MovieUpComingDates = MovieUpComingDates(),
    val page: Int = 0,
    val results: List<MovieUpComingResult> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)

