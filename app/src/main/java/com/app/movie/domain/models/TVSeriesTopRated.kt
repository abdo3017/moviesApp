package com.app.movie.domain.models


import com.app.movie.datasource.network.models.TVSeriesTopRatedResult
import com.google.gson.annotations.SerializedName

data class TVSeriesTopRated(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TVSeriesTopRatedResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)