package com.app.movie.domain.models.tv


import com.app.movie.datasource.network.models.tv.TVSeriesOnTheAirResult

data class TVSeriesOnTheAir(
    val page: Int = 0,
    val results: List<TVSeriesOnTheAirResult> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)

