package com.app.movie.datasource.cache.models.favouritetv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.movie.datasource.network.models.tv.TVSeriesOnTheAirResult
import java.io.Serializable

@Entity(tableName = "FavTVSeriesOnTheAirResult")

data class FavTVSeriesOnTheAirResult(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "id")
    val id: Int = 0
) : Serializable

fun FavTVSeriesOnTheAirResult.asDomainModel() = TVSeriesOnTheAirResult(id = id)
fun List<FavTVSeriesOnTheAirResult>.asDomainModelList(): List<TVSeriesOnTheAirResult> {
    return map {
        TVSeriesOnTheAirResult(id = it.id)
    }
}