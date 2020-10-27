package com.app.movie.domain.models

import com.app.movie.datasource.network.models.MovieNowPlayingDates
import com.app.movie.datasource.network.models.MovieNowPlayingResultsItem

data class MovieNowPlaying(
    val dates: MovieNowPlayingDates? = null,
    val page: Int? = null,
    val totalPages: Int? = null,
    val results: List<MovieNowPlayingResultsItem?>? = null,
    val totalResults: Int? = null
)
