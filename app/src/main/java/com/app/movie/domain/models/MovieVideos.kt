package com.app.movie.domain.models

import com.app.movie.datasource.network.models.MovieVideosResultsItem

data class MovieVideos(
	val id: Int? = null,
	val results: List<MovieVideosResultsItem?>? = null
)

