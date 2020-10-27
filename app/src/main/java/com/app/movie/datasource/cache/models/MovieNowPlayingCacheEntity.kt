package com.app.movie.datasource.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.app.movie.datasource.network.models.MovieNowPlayingDates
import com.app.movie.datasource.network.models.MovieNowPlayingResultsItem
import com.app.movie.utils.ModuleConverter

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



