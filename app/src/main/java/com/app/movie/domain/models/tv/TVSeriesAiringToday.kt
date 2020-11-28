package com.app.movie.domain.models.tv


import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayResult

data class TVSeriesAiringToday(
    val page: Int = 0,
    val results: List<TVSeriesAiringTodayResult> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)

