package com.app.movie.domain.models

import com.app.movie.datasource.network.models.MovieTopRatedResult


data class MovieTopRated(
    val page: Int = 0,
    val results: List<MovieTopRatedResult> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
