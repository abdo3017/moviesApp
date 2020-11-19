package com.app.movie.datasource.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.app.movie.datasource.network.models.MovieTopRatedResult
import com.app.movie.utils.ModuleConverter

@Entity(tableName = "MovieTopRatedCacheEntity")
data class MovieTopRatedCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "page")
    val page: Int = 0,
    @TypeConverters(ModuleConverter::class)
    @field:ColumnInfo(name = "results")
    val results: List<MovieTopRatedResult> = listOf(),
    @field:ColumnInfo(name = "total_pages")
    val totalPages: Int = 0,
    @field:ColumnInfo(name = "totalResults")
    val totalResults: Int = 0
)
