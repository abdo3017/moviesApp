package com.app.movie.datasource.cache.models.favouritetv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayResult
import java.io.Serializable

@Entity(tableName = "FavTVSeriesAiringTodayResult")
data class FavTVSeriesAiringTodayResult(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "id")
    val id: Int = 0
) : Serializable

fun FavTVSeriesAiringTodayResult.asDomainModel() = TVSeriesAiringTodayResult(id = id)
fun List<FavTVSeriesAiringTodayResult>.asDomainModelList(): List<TVSeriesAiringTodayResult> {
    return map {
        TVSeriesAiringTodayResult(id = it.id)
    }
}