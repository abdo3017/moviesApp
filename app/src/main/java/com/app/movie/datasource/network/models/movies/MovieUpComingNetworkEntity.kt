package com.app.movie.datasource.network.models.movies


import com.app.movie.datasource.cache.models.favouritemovies.FavMovieUpComingResult
import com.app.movie.domain.models.movies.FavouriteMovie
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieUpComingNetworkEntity(
    @SerializedName("dates")
    val dates: MovieUpComingDates = MovieUpComingDates(),
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<MovieUpComingResult> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
) : Serializable

data class MovieUpComingResult(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0
) : Serializable

data class MovieUpComingDates(
    @SerializedName("maximum")
    val maximum: String = "",
    @SerializedName("minimum")
    val minimum: String = ""
) : Serializable

fun List<MovieUpComingResult>.asDomainModelList(): List<FavouriteMovie> {
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

fun MovieUpComingResult.asDomainModel() = FavMovieUpComingResult(id = id)
