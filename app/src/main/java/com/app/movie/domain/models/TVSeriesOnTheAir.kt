package com.app.movie.domain.models


import com.app.movie.datasource.network.models.TVSeriesOnTheAirResult

data class TVSeriesOnTheAir(
    val page: Int = 0,
    val results: List<TVSeriesOnTheAirResult> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)

