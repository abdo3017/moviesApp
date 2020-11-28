package com.app.movie.datasource.cache.models.tv


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.utils.ModuleConverter

@Entity(tableName = "TVSeriesTopRatedCacheEntity")
data class TVSeriesTopRatedCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "page")
    val page: Int,
    @TypeConverters(ModuleConverter::class)
    @field:ColumnInfo(name = "results")
    val results: List<TVSeriesTopRatedResult>,
    @field:ColumnInfo(name = "total_pages")
    val totalPages: Int,
    @field:ColumnInfo(name = "total_results")
    val totalResults: Int
)
