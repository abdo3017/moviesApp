package com.app.movie.datasource.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.app.movie.datasource.network.models.MovieVideosResultsItem
import com.app.movie.utils.ModuleConverter

@Entity(tableName = "MovieVideosCacheEntity")
data class MovieVideosCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "id")
    val id: Int? = null,
    @TypeConverters(ModuleConverter::class)
    @field:ColumnInfo(name = "results")
    val results: List<MovieVideosResultsItem?>? = null
)

