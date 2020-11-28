package com.app.movie.domain.models.tv


import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult

data class TVSeriesTopRated(
    val page: Int,
    val results: List<TVSeriesTopRatedResult>,
    val totalPages: Int,
    val totalResults: Int
)