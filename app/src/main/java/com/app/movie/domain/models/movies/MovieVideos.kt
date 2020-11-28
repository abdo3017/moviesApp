package com.app.movie.domain.models.movies

import com.app.movie.datasource.network.models.movies.MovieVideosResultsItem

data class MovieVideos(
	val id: Int? = null,
	val results: List<MovieVideosResultsItem?>? = null
)

