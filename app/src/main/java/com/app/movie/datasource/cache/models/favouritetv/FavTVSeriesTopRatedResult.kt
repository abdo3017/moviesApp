package com.app.movie.datasource.cache.models.favouritetv

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "FavTVSeriesTopRatedResult")

data class FavTVSeriesTopRatedResult(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int = 0
) : Serializable

fun FavTVSeriesTopRatedResult.asDomainModel() = TVSeriesTopRatedResult(id = id)
fun List<FavTVSeriesTopRatedResult>.asDomainModelList(): List<TVSeriesTopRatedResult> {
    return map {
        TVSeriesTopRatedResult(id = it.id)
    }
}