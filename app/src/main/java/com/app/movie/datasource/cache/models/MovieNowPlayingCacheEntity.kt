package com.app.movie.datasource.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.app.movie.utils.ModuleConverter
import com.app.movie.datasource.network.models.MovieNowPlayingDates
import com.app.movie.datasource.network.models.MovieNowPlayingResultsItem

@Entity(tableName = "MovieNowPlayingCacheEntity")
data class MovieNowPlayingCacheEntity(

    @TypeConverters(ModuleConverter::class)
	@field:ColumnInfo(name = "dates")
	val dates: MovieNowPlayingDates? = null,

    @PrimaryKey(autoGenerate = false)
	@field:ColumnInfo(name ="page")
	val page: Int? = null,

    @field:ColumnInfo(name ="total_pages")
	val totalPages: Int? = null,

    @TypeConverters(ModuleConverter::class)
	@field:ColumnInfo(name ="results")
	val results: List<MovieNowPlayingResultsItem?>? = null,

    @field:ColumnInfo(name ="totalResults")
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

