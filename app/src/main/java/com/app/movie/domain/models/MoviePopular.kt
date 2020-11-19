package com.app.movie.domain.models


import com.app.movie.datasource.network.models.MoviePopularResult

data class MoviePopular(
    val page: Int = 0,
    val results: List<MoviePopularResult> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)

