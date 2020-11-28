package com.app.movie.domain.models.tv


import com.app.movie.datasource.network.models.tv.TVSeriesPopularResult

data class TVSeriesPopular(
    val page: Int = 0,
    val results: List<TVSeriesPopularResult> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)

