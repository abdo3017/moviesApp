package com.app.movie.datasource.cache.models.tv


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayResult
import com.app.movie.utils.ModuleConverter

@Entity(tableName = "TVSeriesAiringTodayCacheEntity")
data class TVSeriesAiringTodayCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "page")
    val page: Int = 0,
    @TypeConverters(ModuleConverter::class)
    @field:ColumnInfo(name = "results")
    val results: List<TVSeriesAiringTodayResult> = listOf(),
    @field:ColumnInfo(name = "total_pages")
    val totalPages: Int = 0,
    @field:ColumnInfo(name = "totalResults")
    val totalResults: Int = 0
)

