package com.app.movie.domain.models.movies


import com.app.movie.datasource.network.models.movies.MoviePopularResult

data class MoviePopular(
    val page: Int = 0,
    val results: List<MoviePopularResult> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)

