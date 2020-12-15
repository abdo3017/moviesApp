package com.app.movie.datasource.network.models.tv


import com.app.movie.datasource.cache.models.favouritetv.FavTVSeriesTopRatedResult
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TVSeriesTopRatedNetworkEntity(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<TVSeriesTopRatedResult>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int
)

data class TVSeriesTopRatedResult(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: List<String>? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
) : Serializable

fun TVSeriesTopRatedResult.asDomainModel() = FavTVSeriesTopRatedResult(id = id)
