package com.app.movie.datasource.cache.models.favouritetv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.movie.datasource.network.models.tv.TVSeriesPopularResult
import java.io.Serializable

@Entity(tableName = "FavTVSeriesPopularResult")

data class FavTVSeriesPopularResult(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "id")
    val id: Int = 0
) : Serializable

fun FavTVSeriesPopularResult.asDomainModel() = TVSeriesPopularResult(id = id)
fun List<FavTVSeriesPopularResult>.asDomainModelList(): List<TVSeriesPopularResult> {
    return map {
        TVSeriesPopularResult(id = it.id)
    }
}