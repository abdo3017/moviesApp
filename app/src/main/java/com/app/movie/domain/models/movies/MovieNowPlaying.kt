package com.app.movie.domain.models.movies

import com.app.movie.datasource.network.models.movies.MovieNowPlayingDates
import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem

data class MovieNowPlaying(
    val dates: MovieNowPlayingDates? = null,
    val page: Int? = null,
    val totalPages: Int? = null,
    val results: List<MovieNowPlayingResultsItem> = listOf(),
    val totalResults: Int? = null
)
