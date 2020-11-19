package com.app.movie.datasource.network.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieNowPlayingNetworkEntity(

    @field:SerializedName("dates")
    val dates: MovieNowPlayingDates? = null,

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<MovieNowPlayingResultsItem> = listOf(),

    @field:SerializedName("total_results")
    val totalResults: Int? = null
) : Serializable

data class MovieNowPlayingDates(

	@field:SerializedName("maximum")
	val maximum: String? = null,

	@field:SerializedName("minimum")
	val minimum: String? = null
) : Serializable

data class MovieNowPlayingResultsItem(

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	@field:SerializedName("original_title")
	val originalTitle: String? = null,

	@field:SerializedName("video")
	val video: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int?>? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("adult")
	val adult: Boolean? = null,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null
) : Serializable
//
//import com.squareup.moshi.Json
//import com.squareup.moshi.JsonClass
//import com.squareup.moshi.JsonQualifier
//
//@JsonQualifier
//@Retention(AnnotationRetention.RUNTIME)
//@Target(AnnotationTarget.FIELD)
//internal annotation class JsonObjectOrFalse
//
//@JsonClass(generateAdapter = true)
//data class MovieNowPlayingNetworkEntity(
//
//	@Json(name="dates")
//	@field:JsonObjectOrFalse
//	val dates: MovieNowPlayingDates? = null,
//
//	@Json(name="page")
//	val page: Int? = null,
//
//	@Json(name="total_pages")
//	val totalPages: Int? = null,
//
//	@Json(name="results")
//	val results: List<MovieNowPlayingResultsItem?>? = null,
//
//	@Json(name="total_results")
//	val totalResults: Int? = null
//)
//@JsonClass(generateAdapter = true)
//data class MovieNowPlayingResultsItem(
//
//	@Json(name="overview")
//	val overview: String? = null,
//
//	@Json(name="original_language")
//	val originalLanguage: String? = null,
//
//	@Json(name="original_title")
//	val originalTitle: String? = null,
//
//	@Json(name="video")
//	val video: Boolean? = null,
//
//	@Json(name="title")
//	val title: String? = null,
//
//	@Json(name="genre_ids")
//	val genreIds: List<Int?>? = null,
//
//	@Json(name="poster_path")
//	val posterPath: String? = null,
//
//	@Json(name="backdrop_path")
//	val backdropPath: String? = null,
//
//	@Json(name="release_date")
//	val releaseDate: String? = null,
//
//	@Json(name="popularity")
//	val popularity: Double? = null,
//
//	@Json(name="vote_average")
//	val voteAverage: Double? = null,
//
//	@Json(name="id")
//	val id: Int? = null,
//
//	@Json(name="adult")
//	val adult: Boolean? = null,
//
//	@Json(name="vote_count")
//	val voteCount: Int? = null
//)
//@JsonClass(generateAdapter = true)
//data class MovieNowPlayingDates(
//
//	@Json(name="maximum")
//	val maximum: String? = null,
//
//	@Json(name="minimum")
//	val minimum: String? = null
//)
