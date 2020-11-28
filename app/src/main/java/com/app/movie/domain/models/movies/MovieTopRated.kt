package com.app.movie.domain.models.movies

import com.app.movie.datasource.network.models.movies.MovieTopRatedResult


data class MovieTopRated(
    val page: Int = 0,
    val results: List<MovieTopRatedResult> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
