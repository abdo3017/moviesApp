package com.app.movie.domain.models


import com.app.movie.datasource.network.models.TVSeriesPopularResult

data class TVSeriesPopular(
    val page: Int = 0,
    val results: List<TVSeriesPopularResult> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)

