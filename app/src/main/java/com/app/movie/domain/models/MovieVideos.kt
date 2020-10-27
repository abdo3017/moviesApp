package com.app.movie.domain.models

import com.app.movie.datasource.network.models.MovieVideosResultsItem
import com.google.gson.annotations.SerializedName

data class MovieVideos(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("results")
	val results: List<MovieVideosResultsItem?>? = null
)

