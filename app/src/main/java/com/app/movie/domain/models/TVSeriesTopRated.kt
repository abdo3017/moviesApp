package com.app.movie.domain.models


import com.app.movie.datasource.network.models.TVSeriesTopRatedResult

data class TVSeriesTopRated(
    val page: Int,
    val results: List<TVSeriesTopRatedResult>,
    val totalPages: Int,
    val totalResults: Int
)