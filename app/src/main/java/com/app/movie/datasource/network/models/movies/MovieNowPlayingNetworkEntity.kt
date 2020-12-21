package com.app.movie.datasource.network.models.movies

import com.app.movie.datasource.cache.models.favouritemovies.FavMovieNowPlayingResultsItem
import com.app.movie.domain.models.movies.FavouriteMovie
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

fun List<MovieNowPlayingResultsItem>.asDomainModelList(): List<FavouriteMovie> {
    return map {
        FavouriteMovie(
            id = it.id,
            video = it.video,
            title = it.title,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath
        )
    }
}

fun MovieNowPlayingResultsItem.asDomainModel() = FavMovieNowPlayingResultsItem(id = id)

