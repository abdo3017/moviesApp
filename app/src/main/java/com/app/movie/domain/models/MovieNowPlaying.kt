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

data class MovieNowPlayingDates(
	val maximum: String? = null,
	val minimum: String? = null
)

data class MovieNowPlayingResultsItem(
	val overview: String? = null,
	val originalLanguage: String? = null,
	val originalTitle: String? = null,
	val video: Boolean? = null,
	val title: String? = null,
	val genreIds: List<Int?>? = null,
	val posterPath: String? = null,
	val backdropPath: String? = null,
	val releaseDate: String? = null,
	val popularity: Double? = null,
	val voteAverage: Double? = null,
	val id: Int? = null,
	val adult: Boolean? = null,
	val voteCount: Int? = null
)

